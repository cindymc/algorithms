package designpatterns.state;

/**
 * Created by cindy on 7/31/16.
 */
public class KidWithGum
{
    public static void main(String [] args)
    {
        GumBallMachine gbm = new GumBallMachine();
        gbm.insertQuarter();
        gbm.turnCrank();
    }
}
