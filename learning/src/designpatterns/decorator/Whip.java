package designpatterns.decorator;

/**
 * Created by cindymc on 7/28/16.
 */
public class Whip extends CondimentDecorator
{
    Beverage beverage;

    public Whip(Beverage beverage){this.beverage=beverage;}
    public String getDescription(){return "Whip";}
    public double cost(){return beverage.cost() + .30;}
}
