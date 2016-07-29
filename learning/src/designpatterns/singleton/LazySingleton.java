package designpatterns.singleton;

/**
 * May have some associated costs to create, so don't it until we need it (lazy construction0.
 * Created by cindy on 7/29/16.
 */
public class LazySingleton
{
    // private ctor
    private static volatile LazySingleton instance;
    private LazySingleton(){}

    // Construct if doesn't exist, but now need so synchronze
    public static LazySingleton getInstance()
    {
        // Check first time
        if (instance == null)
        {
            // synchonize on this class
            synchronized(LazySingleton.class)
            {
                if (instance==null)
                {
                    instance = new LazySingleton();

                    // The creation of LazySingleton may be expensive, but we only pay the synch cost once.
                }
            }
        }
    }


}
