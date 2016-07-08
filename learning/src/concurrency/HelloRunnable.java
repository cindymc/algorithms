
/**
 * @author Copyright (c) 2013 by Oracle, Inc. All Rights Reserved.
 */
public class HelloRunnable extends BaseRunnable implements Runnable
{
    public void run()
    {
        try
        {
            Thread.sleep(ConcurrencyUtils.randomSleep()*1000);
        }
        catch (InterruptedException e)
        {
            System.err.println(this + " thread interrupted.");
        }
        System.out.println("Crush it!! " + (nextCount()));
    }
}
