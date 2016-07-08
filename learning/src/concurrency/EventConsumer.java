
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Intended to be used in a single thread.
 *
 * @author Copyright (c) 2013 by Oracle, Inc. All Rights Reserved.
 */
public class EventConsumer implements Runnable
{
    BlockingQueue<Integer> queue;
    CountDownLatch doneSignal;
    int batchSize = 20;

    public EventConsumer(BlockingQueue<Integer> queue, CountDownLatch doneSignal)
    {
        this.queue = queue;
        this.doneSignal = doneSignal;
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

            // todo: we can't really shut down when the queue is drained; we need to wait for more incoming events
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // Finished
        doneSignal.countDown();
    }

    // Fill up a List of events to batch persist.  If it's not filled in N loops, persist what we have
    private void process () throws InterruptedException
    {
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

    // todo: persistence needs some sort of timeout parameter
    private void persist(Integer i) throws InterruptedException
    {
        System.err.println("Persist: " + i);
        Thread.sleep(500);
    }
}
