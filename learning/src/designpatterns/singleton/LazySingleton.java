package designpatterns.singleton;

/**
 * May have some associated costs to create, so don't it until we need it (lazy construction).
 * https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
 *
 * Created by cindy on 7/29/16.
 */
public class LazySingleton
{
    // Important that this is volatile.  Volatile implies that only one thread can modify this variable (it's essentially
    // a lock on the variable.  It cannot be held in any thread's cache.  It will not ever be partially constructed, so
    // other threads will either see null, or the object (if it is constructed)
    private static volatile LazySingleton instance;

    // All Singletons have private ctors
    private LazySingleton()
    {
        // Heavy lifting done here
    }

    // Double-checked locking
    // First check is to see whether we need to lock to create the object.  Because we're using a volatile variable,
    // we'll never see a partially-constructed instance.
    public static LazySingleton getInstance()
    {
        if (instance == null)
        {
            // Always lock on the Class, not the Object!
            synchronized (LazySingleton.class)
            {
                // Check again to make sure no other thread has created instance in between
                if (instance == null)
                {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

}
