package designpatterns.singleton;

/**
 * May have some associated costs to create, so don't it until we need it (lazy construction).
 * https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
 *
 * Created by cindy on 7/29/16.
 */
public class LazySingleton
{
    private LazySingleton(){}

    private static class LazyHolder
    {
        private static final LazySingleton instance = new LazySingleton();
    }

    // Construct if doesn't exist, but now need so synchronze
    public static LazySingleton getInstance()
    {
        return LazyHolder.instance;
    }

}
