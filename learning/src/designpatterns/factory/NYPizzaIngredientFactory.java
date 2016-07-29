package designpatterns.factory;

/**
 * Created by cindymc on 7/28/16.
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory
{
    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new TomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozarellaCheese();
    }
}
