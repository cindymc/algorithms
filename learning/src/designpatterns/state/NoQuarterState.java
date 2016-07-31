package designpatterns.state;

/**
 * Created by cindy on 7/31/16.
 */
public class NoQuarterState implements State
{
    GumBallMachine gbm;

    public NoQuarterState(GumBallMachine gbm){this.gbm = gbm;}

    @Override
    public void insertQuarter()
    {
        System.err.println("Quarter inserted");
        gbm.setState(gbm.hasQuarterState);
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
