package designpatterns.state;

/**
 * Created by cindy on 7/31/16.
 */
public class SoldState implements State
{
    GumBallMachine gbm;

    public SoldState(GumBallMachine gbm){this.gbm = gbm;}

    @Override
    public void insertQuarter() {
        gbm.setState(gbm.soldState);
    }

    @Override
    public void ejectQuarter() {

    }

    @Override
    public void turnCrank() {

    }

    @Override
    public void dispense() {
        System.err.println("Here's your gum!");
        gbm.releaseBall();
    }
}
