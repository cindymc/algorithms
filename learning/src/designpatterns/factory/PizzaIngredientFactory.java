package designpatterns.factory;

/**
 * Created by cindymc on 7/28/16.
 */
public interface PizzaIngredientFactory
{
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
}
