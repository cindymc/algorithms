package designpatterns.factory;

/**
 * Created by cindymc on 7/28/16.
 */
public class Pizza
{
    Dough dough;
    Sauce sauce;
    Cheese cheese;

    public void assemble(Dough dough, Sauce sauce, Cheese cheese)
    {
       this.dough = dough;
       this.sauce = sauce;
        this.cheese = cheese;
    }

    public void serve()
    {
        System.err.println("Yummy!  " + dough + " dough, " + sauce + " sauce, " + cheese + ", cheese");
    }
}
