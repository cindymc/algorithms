package lambdas;

import java.util.Comparator;

/**
 * Created by cindymc on 8/9/16.
 */
public class AppleComparator
{
    public static void main(String [] args)
    {
        Apple apple1 = new Apple(100);
        Apple apple2 = new Apple(300);

        // Comparator with lambdas
        Comparator<Apple> comparator = (Apple a1, Apple a2) -> ((Integer)(a1.weight)).compareTo(a2.weight);
        int rv = comparator.compare(apple1, apple2);
        System.err.println("Result: " + rv);   // -1,  since a1 is smaller
    }

    /**
     * A non-static inner class has full access to the members of the class within which it is nested.
     * A static inner class does not have a reference to its enclosing instance, so it cannot
     * invoke non-static methods or access non-static fields of an instance of the class within which it is nested.
     */
    static class Apple
    {
        int weight;
        Apple(int weight){this.weight = weight;}
    }
}
