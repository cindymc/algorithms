package designpatterns.singleton;

/**
 * Created up-front, minimal resources (cheap to construct).
 * Created by cindy on 7/29/16.
 */
public class EagerSingleton
{
    // private ctor
    private EagerSingleton(){}

    // create statically when EagerSingleton is created.  Static initializers are guaranteed to be thread-safe
    public static final EagerSingleton instance = new EagerSingleton();

    // Accessor
    public static EagerSingleton getInstance() {return instance;}
}
