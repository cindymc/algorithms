package designpatterns.state;

/**
 * Created by cindy on 7/31/16.
 */
public class HasQuarterState implements State
{
    GumBallMachine gbm;

    public HasQuarterState(GumBallMachine gbm){this.gbm = gbm;}

    @Override
    public void insertQuarter() {
        System.err.println("You can't insert another");
    }

    @Override
    public void ejectQuarter()
    {
        System.err.println("Ejecting quarter...");
        gbm.setState(gbm.noQuarterState);
    }

    @Override
    public void turnCrank() {
        System.err.println("Turning Crank");
        gbm.setState(gbm.soldState);
    }

    @Override
    public void dispense() {
        System.err.println("Can't dispense until you turn crank.");
    }
}
