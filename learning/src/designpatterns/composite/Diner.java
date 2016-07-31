package designpatterns.composite;

import java.util.Iterator;

/**
 * Test class
 * Created by cindy on 7/31/16.
 */
public class Diner
{
    public static void main(String [] args)
    {
        MenuComponent allMenus = new Menu("Sal's Diner");

        MenuComponent breakfast = new Menu("BREAKFAST");
        MenuComponent lunch = new Menu("LUNCH");
        MenuComponent dinner = new Menu("DINNER");

        breakfast.add(new MenuItem("Bacon"));
        breakfast.add(new MenuItem("Eggs"));
        breakfast.add(new MenuItem("Grits"));

        lunch.add(new MenuItem("HamAndCheese"));
        lunch.add(new MenuItem("Salad"));
        lunch.add(new MenuItem("Soup"));

        dinner.add(new MenuItem("Cocktails"));

        allMenus.add(breakfast);
        allMenus.add(lunch);
        allMenus.add(dinner);

        allMenus.print();
    }
}
