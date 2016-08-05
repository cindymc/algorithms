package designpatterns.singleton;

/**
 * Created by cindy on 8/5/16.
 */
public class CloneableSingleton implements CloneableInterface
{
    private static final CloneableSingleton instance = new CloneableSingleton();
    private CloneableSingleton(){}
    public static CloneableSingleton getInstance(){return instance;}

    @Override
    public CloneableSingleton clone()
    {
        throw new IllegalStateException("Don't clone me");

        // Don't do this!
        //System.err.println("You cloned me");
        //return new CloneableSingleton();
    }
}
