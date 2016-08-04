package concurrency.java8;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by cindymc on 8/4/16.
 */
public class LambdaCallable
{
    Callable<Integer> task = () -> {
        try
        {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e){System.err.println("Interrupted");}
        return 123;
    };

}
