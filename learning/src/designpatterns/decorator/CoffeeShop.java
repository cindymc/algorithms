package designpatterns.decorator;

/**
 * Created by cindymc on 7/28/16.
 */
public class CoffeeShop
{
    public static void main(String [] args)
    {
        // Start with espresso
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        // A House Blend coffee with Mocha and double Whip
        Beverage hb = new HouseBlend();

        // Here's the trick: wrap it, but return reference to the same object
        hb = new Mocha(hb);
        hb = new Whip(hb);
        hb = new Whip(hb);
        System.out.println(hb.getDescription() + " $" + String.format("%.2f",hb.cost()));
    }
}
