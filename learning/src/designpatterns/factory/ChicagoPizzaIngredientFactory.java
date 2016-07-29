package designpatterns.factory;

/**
 * Created by cindymc on 7/28/16.
 */
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory
{
    @Override
    public Dough createDough() {
        return null;
    }

    @Override
    public Sauce createSauce() {
        return null;
    }

    @Override
    public Cheese createCheese() {
        return null;
    }
}
