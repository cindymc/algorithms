package concurrency.java6;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Intended to be used in a single thread, so this class is NOT thread-safe.
 *
 * @author Copyright (c) 2013 by Oracle, Inc. All Rights Reserved.
 */
public class ScheduledEventConsumer implements Runnable
{
    BlockingQueue<Integer> queue;
    int batchSize = 20;
    Random random = new Random();
    Semaphore connectionLock = new Semaphore(1);

    public ScheduledEventConsumer(BlockingQueue<Integer> queue)
    {
        this.queue = queue;
    }

    // Use Semaphore to check whether we're ready to shut down now
    public void shutDown() throws InterruptedException
    {
        // todo: prevent more events from being added to the queue
        System.err.println("Shutting down consumer...");

        // Try for 30 seconds (todo: maybe we need to try for a longer period of time?)
        for (int i=0;i<10; i++)
        {
            if (connectionLock.tryAcquire(3, TimeUnit.SECONDS))
            {
                System.err.println("All connections are closed.  Ready to shut down.");
                break;
            }
        }

        // Drain queue
        System.err.println("Processing remaining events in queue: " + queue.size());
        if (!queue.isEmpty())
        {
            process();
        }
        System.err.println("Ready to shut down");
    }

    public void run()
    {
        System.err.println("Consumer started...");

        try
        {
            // Wait until queue starts to fill
            while (queue.peek() ==null)
            {
                Thread.sleep(1000);
            }

            // Ready to start processing events off queue
            do
            {
                System.err.println("Queue size is: " + queue.size());
                process();
            }
            while (!queue.isEmpty());

            System.err.println("Finished processing queue.");

            // todo: cancel on timed out persistence or exceptions
        }
        catch (InterruptedException e)
        {
            // Check condition of queue.  If not empty yet, process the remaining events
        }
    }

    // Fill up a List of events to batch persist.  If it's not filled in N loops, persist what we have
    private void process () throws InterruptedException
    {
        try
        {
            connectionLock.acquire();

            System.err.println("Getting a connection...");

            // Sometimes, we can't fill it, so only loop N times before persisting what we have.
            int count = 0;
            do
            {
                Integer i = queue.poll(1, TimeUnit.SECONDS);
                if (i!=null)
                {
                    persist(i);
                }
                else
                {
                    // timed out before something in queue
                    System.err.println("Queue is empty");
                }
            } while (++count <= batchSize);

            System.err.println("Closing connection...");
        }
        finally
        {
            connectionLock.release();
        }
    }

        // todo: persistence needs some sort of timeout parameter?  Or maybe that's configurable via the Jena APIs...
    private void persist(Integer i) throws InterruptedException
    {
        try
        {
            System.err.println("Persist: " + i);
            Thread.sleep(random.nextInt(500));
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
