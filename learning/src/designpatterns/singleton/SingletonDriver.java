package designpatterns.singleton;

/**
 * Created by cindy on 8/5/16.
 */
public class SingletonDriver
{
    public static void main(String [] args)
    {
        //CloneableSingleton s = CloneableSingleton.getInstance();
        //s.clone();

        EnumSingleton s = EnumSingleton.INSTANCE;
        s.leaveTheBuilding();
    }
}
