package concurrency.java6;

/**
 * @author Copyright (c) 2013 by Oracle, Inc. All Rights Reserved.
 */
class BaseRunnable
{
    static int count;

    // Use a simple object to lock on
    static final Byte countLock = Byte.valueOf("1");

    static int nextCount()
    {
        synchronized(countLock)
        {
             ++count;
        }
        return count;
    }
}
