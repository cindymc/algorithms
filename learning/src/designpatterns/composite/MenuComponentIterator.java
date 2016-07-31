package designpatterns.composite;

import java.util.Iterator;
import java.util.List;

/**
 * Created by cindy on 7/31/16.
 */
public class MenuComponentIterator implements Iterator <MenuComponent>
{
    MenuComponent[] stuff;
    int index = 0;

    public MenuComponentIterator(List<MenuComponent> list)
    {
        stuff = list.toArray(new MenuComponent[0]);

    }

    public MenuComponent next()
    {
        return stuff[index++];
    }
    public boolean hasNext()
    {
        return index < stuff.length;
    }
}
