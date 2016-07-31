package designpatterns.composite;

import java.util.Iterator;

/**
 * Created by cindy on 7/31/16.
 */
public abstract class MenuComponent
{
    String desc;

    public MenuComponent(String desc){this.desc = desc;}

    // Define a bunch of these with default impl to throw runtime exception
    public void add(MenuComponent menuComponent){throw new UnsupportedOperationException();}
    public Iterator createIterator(){throw new UnsupportedOperationException();}

    public void print() {System.err.println(desc);}

}
