package designpatterns.factory;

/**
 * Created by cindymc on 7/28/16.
 */
public class NYPizzaStore implements PizzaStore
{
    @Override
    public Pizza createPizza(String style)
    {
        PizzaIngredientFactory factory;
        if (style.equals("NY"))
        {
           factory = new NYPizzaIngredientFactory();
        }
        else
        {
            factory = new ChicagoPizzaIngredientFactory();
        }

        Dough dough = factory.createDough();
        Sauce sauce = factory.createSauce();
        Cheese cheese = factory.createCheese();

        Pizza pizza = new Pizza();
        pizza.assemble(dough, sauce, cheese);
        pizza.serve();

        return pizza;
    }
}
