package designpatterns.template;

/**
 * Created by cindy on 7/31/16.
 */
public abstract class CaffienatedBeverage
{
     void boilWater(){System.err.println("BOIL WATER");}
     void brew(){System.err.println("BREW");}
     abstract void prepare();
}
