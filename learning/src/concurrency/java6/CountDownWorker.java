package concurrency.java6;

import java.util.concurrent.CountDownLatch;

/**
 * @author Copyright (c) 2013 by Oracle, Inc. All Rights Reserved.
 */
public class CountDownWorker extends BaseRunnable implements Runnable
{
    CountDownLatch startSignal;
    CountDownLatch doneSignal;

    public CountDownWorker(CountDownLatch doneSignal)
    {
        this.doneSignal = doneSignal;
    }

    public CountDownWorker(CountDownLatch startSignal, CountDownLatch doneSignal)
    {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public void run()
    {
        // startSignal.await();
        int i = nextCount();
        System.out.println("CountDownWorker: " + i + " started");
        try
        {
            Thread.sleep(1000*ConcurrencyUtils.randomSleep());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        doneSignal.countDown();
        System.out.println("CountDownWorker: " + i + " finished");
    }

}
