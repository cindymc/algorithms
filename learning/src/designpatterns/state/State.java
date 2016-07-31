package designpatterns.state;

/**
 * Created by cindy on 7/31/16.
 */
public interface State
{
    // GOTO HasQuarterState
    void insertQuarter();

    // GOTO NoQuarterState
    void ejectQuarter();

    // GOTO SoldState
    void turnCrank();

    // Dispense gumball.  Decrement count.  Go to SoldOutState if empty, otherwise NoQuarterState
    void dispense();

}
