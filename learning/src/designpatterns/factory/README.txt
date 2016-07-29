The Factory Method pattern defines an interface for creating an object, but lets subclasses decide which class to
instantiate.

An AbstractFactory gives us an interface to create a family of products, without specifying their concrete classes.
This allows us to implement a variety of factories that produce products for different contexts (different regions,
products, etc).

Because our code is decoupled from the actual product, we can substitute different factories to get different contexts.

For example:

A (NY) PizzaStore uses a (NY) PizzaIngredientFactory to get ingredients for, and assemble, a NY-style pizza.
A (Chicago) PizzaStore uses a (Chicago) PizzaIngredientFactory to get ingredients for, and assemble, a Chicago-style pizza.