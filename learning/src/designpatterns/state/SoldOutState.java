package designpatterns.state;

/**
 * Created by cindy on 7/31/16.
 */
public class SoldOutState implements State
{
    GumBallMachine gbm;

    public SoldOutState(GumBallMachine gbm){this.gbm = gbm;}

    @Override
    public void insertQuarter() {
        gbm.setState(gbm.soldOutState);
    }

    @Override
    public void ejectQuarter() {

    }

    @Override
    public void turnCrank() {

    }

    @Override
    public void dispense() {

    }
}
