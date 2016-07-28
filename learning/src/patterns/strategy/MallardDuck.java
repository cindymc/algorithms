package patterns.strategy;

/**
 * Created by cindy on 7/27/16.
 */
public class MallardDuck extends Duck
{
    public MallardDuck()
    {
        flyBehavior = new FlyWithWings();
        soundBehavior = new Quack();
    }

}
