package designpatterns.strategy;
//import junit.org.Test;

/**
 * Created by cindy on 7/27/16.
 */
public class StrategyTest
{
   // @Test
    public static void main(String [] args)
    {
        // Defaults
        Duck duck = new MallardDuck();
        duck.display();

        // Change its behavior
        duck.setFlyBehavior(new FlyNoWay());
        duck.setSoundBehavior(new Squeak());
        duck.display();
    }
}
