
import java.util.concurrent.Callable;

/**
 * @author Copyright (c) 2013 by Oracle, Inc. All Rights Reserved.
 */
public class LongRunningCallable implements Callable<String>
{

    public String call() throws Exception
    {
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            System.err.println(this + " thread interrupted.");
        }
        return "Finished!";
    }
}
