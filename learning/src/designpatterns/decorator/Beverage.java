package designpatterns.decorator;

/**
 * Created by cindymc on 7/28/16.
 */
public abstract class Beverage
{
    String description = "Unknown Beveage";

    public String getDescription(){return description;}

    public abstract double cost();
}
