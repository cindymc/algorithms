package designpatterns.state;

/**
 * Created by cindy on 7/31/16.
 */
public class GumBallMachine
{
    int numBalls;
    State hasQuarterState;
    State noQuarterState;
    State soldOutState;
    State soldState;

    // Current state
    State state;

    public GumBallMachine()
    {
        numBalls = 10;
        hasQuarterState = new HasQuarterState(this);
        noQuarterState = new NoQuarterState(this);
        soldOutState = new SoldOutState(this);
        soldState = new SoldState(this);

        // Init
        state = noQuarterState;
    }

    public void insertQuarter() {state.insertQuarter();}
    public void ejectQuarter(){state.ejectQuarter();}
    public void turnCrank(){state.turnCrank(); state.dispense();}

    void setState(State state){this.state = state;}

    void releaseBall()
    {
        if (state.equals(soldOutState))
        {
            System.err.println("Machine is empty");
            return;
        }

        --numBalls;
        if (numBalls == 0)
        {
            state = soldOutState;
        }
        else
        {
            state = noQuarterState;
        }
    }
}
