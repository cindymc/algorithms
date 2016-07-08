import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 */
public class ThreadPoolExecutorTest
{
    // todo: cached pool, fixed thread pool, scheduled pool
    // return a future from the Callable
    // manipulating Futures

    /**
     * Basic ExecutorService stuff
     * @throws Exception
     */
    public void testSimpleThreads() throws Exception
    {
        // Prefer to work with Executors utility methods
        // Creates an Executor that uses a single worker thread operating off an unbounded queue.
        // An Executor executes submitted Runnable tasks
        // ExecutorService extends Executor, and adds ability to shut down

        // Let's just use a single thread for now
        ExecutorService pool = Executors.newSingleThreadExecutor();

        // Create a Callable task.  Callable extends Runnable, but returns a value and may throw an exception.
        // This method is just to wrap the Runnable so we can pass a Callable to APIs that require it.
        Callable<Object> c = Executors.callable(new HelloRunnable());

        // Now submit it, and get a Future as return value.  A Future represents the future of an async operation
        Future future = pool.submit(c);

        // Let's ask the future about it.  Wait until it's done
        Object o = future.get();

        // Once we get it, it should be done.
        System.err.println("Done? " + future.isDone());

        pool.shutdownNow();
    }

    /**
     * Test with a Callable returning a future
     */
    public void testCallable() throws Exception
    {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<String> future = pool.submit(new SimpleCallable());
        String msg = future.get();
        System.err.println(msg);
        pool.shutdownNow();
    }

    /**
     * Test with a long-running Callable returning a future
     */
    public void testLongRunningCallable() throws Exception
    {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<String> future = pool.submit(new LongRunningCallable());

        // Wait only 3 seconds before timing out (Callable sleeps 5 seconds)
        try
        {
            future.get(3, TimeUnit.SECONDS);
        }
        catch (TimeoutException e)
        {
            // Even though it timed out, it's still not cancelled
            System.err.println("Cancelled? " + future.isCancelled());

            if (!future.isCancelled())
            {
                future.cancel(true);
            }
            System.err.println("Cancelled? " + future.isCancelled());
        }

        // We also could have explictly cancelled it:  future.cancel(true)
        pool.shutdownNow();
    }

    private void testLotsaJobs() throws Exception
    {
        List<Runnable> jobs = getJobs();
//        ExecutorService pool = Executors.newFixedThreadPool(2);
        ExecutorService pool = Executors.newCachedThreadPool();
        for (Runnable job : jobs)
        {
            pool.submit(job);
        }
        pool.shutdownNow();
    }

    private void testCountDown() throws Exception
    {
        int numTasks = 10;
//        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(numTasks);

        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i=0; i<numTasks; i++)
        {
            pool.execute(new CountDownWorker(doneSignal));
        }

        // Could signal all to start together, but we don't need to do that

        // Wait for all to finish
        doneSignal.await();

        System.out.println("All jobs finished");
        pool.shutdownNow();
    }

    private void sayHello()
    {
        System.err.println("Hello, Boulder!");
    }

    private List<Runnable> getJobs()
    {
        List<Runnable> runnableList = new ArrayList<Runnable>();
        for (int i=0; i<10; i++)
        {
             runnableList.add(new HelloRunnable());
        }
        return runnableList;
    }

    // Simple producer/consumer example via a queue
    private void testQueueSimple()
    {
        // todo: how would you handed a bounded queue without blocking the thread that's calling RDFGraphManager?
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();  // unbounded.
        EventProducer producer = new EventProducer(queue);

        // Add a signal to tell us when things are done (all events processed)
        CountDownLatch doneSignal = new CountDownLatch(1);
        EventConsumer consumer = new EventConsumer(queue, doneSignal);

        // Start a consumer thread
        // todo: try this with a ScheduledExecutorService so we can drain the queue on a given time interval.
        Thread t = new Thread(consumer);
        t.start();

        // Put some stuff in the queue
        Random random = new Random();
        for (int i=0; i<100; i++)
        {
            producer.addEvent(random.nextInt(1000));
        }

        // Terminate the consumer thread when producer is done
        try{doneSignal.await();}catch(InterruptedException e){}

        System.err.println("Terminating thread...");
        t.interrupt();
    }

    // This one also uses a queue for the consumer, but it runs on a given time interval
    private void testQueueScheduled()
    {
        // todo: how would you handed a bounded queue without blocking the thread that's calling RDFGraphManager?
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();  // unbounded.
        EventProducer producer = new EventProducer(queue);

        // Consumer, which will stay alive
        ScheduledEventConsumer consumer = new ScheduledEventConsumer(queue);

        // Start a consumer thread an execute it at timed intervals (eg, every 15 seconds)
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> consumerFuture = scheduler.scheduleAtFixedRate(consumer, 0, 15, TimeUnit.SECONDS);

        // Put some stuff in the queue
        Random random = new Random();
        for (int i=0; i<50; i++)
        {
            producer.addEvent(random.nextInt(1000));
        }

        // Sleep a bit and add some more events
        try{Thread.sleep(30000);}catch(InterruptedException e){}
        for (int i=0; i<100; i++)
        {
            producer.addEvent(random.nextInt(1000));
        }

        // Sleep a bit.
        try{Thread.sleep(20000);}catch(InterruptedException e){}

        // In production, we won't cancel these until app shuts down.  We'll also need to make sure the database
        // connections are all closed, the queue is empty, etc
        do
        {
            try{Thread.sleep(10000);}catch(InterruptedException e){}
        } while (!queue.isEmpty());

        System.err.println("Canceling consumer");
        try{consumer.shutDown();}catch(InterruptedException e){}

        consumerFuture.cancel(true);

        System.err.println("Terminating scheduler");
        scheduler.shutdown();
    }

    // See 'jdb', the java debugger
    public static void main(String [] args) throws Exception
    {
        ThreadPoolExecutorTest test = new ThreadPoolExecutorTest();
        test.testQueueScheduled();

        // todo: start working with LinkedBlockingQueue.  How is it different from ArrayBlockingQueue?
        // Linked queues typically have higher throughput than array-based queues but less predictable performance
        // in most concurrent applications.  Linked can grow arbitrarily large (until it exceeds capacity).  Array
        // blocking queue is of a fixed length
    }
}
