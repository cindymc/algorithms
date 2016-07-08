
import java.util.concurrent.Callable;

/**
 * @author Copyright (c) 2013 by Oracle, Inc. All Rights Reserved.
 */
public class SimpleCallable extends BaseRunnable implements Callable<String>
{
    public String call() throws Exception
    {
        try
        {
            Thread.sleep(ConcurrencyUtils.randomSleep()*1000);
        }
        catch (InterruptedException e)
        {
            System.err.println(this + " thread interrupted.");
        }
        return "Concurrency is fun! " + (nextCount());
    }
}
