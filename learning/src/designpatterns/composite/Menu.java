package designpatterns.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cindy on 7/31/16.
 */
public class Menu extends MenuComponent
{
    public Menu(String desc){super(desc);}

    List<MenuComponent> menuItems = new ArrayList<>();

    public void add(MenuComponent menuItem){menuItems.add(menuItem);}

    public Iterator<MenuComponent> createIterator() { return menuItems.iterator();}

    @Override
    // Recurse over items
    public void print()
    {
        System.err.println("\n " + desc + ":");
        Iterator<MenuComponent> iter = createIterator();
        while(iter.hasNext())
        {
            iter.next().print();
        }
    }

}
