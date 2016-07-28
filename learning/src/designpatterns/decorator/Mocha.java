package designpatterns.decorator;

/**
 * Created by cindymc on 7/28/16.
 */
public class Mocha extends CondimentDecorator
{
    Beverage beverage;

    public Mocha(Beverage beverage){this.beverage=beverage;}
    public String getDescription(){return "Mocha";}
    public double cost(){return beverage.cost() + .20;}
}
