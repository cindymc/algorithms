
import java.util.Random;

/**
 * @author Copyright (c) 2013 by Oracle, Inc. All Rights Reserved.
 */
public class ConcurrencyUtils
{
    static Random r = new Random();
    static int randomSleep()
    {
        return r.nextInt(5);
    }

    static int randomSleep(int max)
    {
        return r.nextInt(max);
    }
}
