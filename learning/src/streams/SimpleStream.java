package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by cindymc on 8/1/16.
 */
public class SimpleStream
{
    public static void main(String [] args)
    {
        Random r = new Random();

        // Create a List of Apples
        List<Apple> apples = new ArrayList<>(1000);
        for (int i=0; i<1000; i++)
        {
            apples.add(new Apple(r.nextInt(200)));
        }

        // Filter smaller ones
        List<Apple> heavyApples = apples.stream()
                .filter((Apple a) -> a.weight > 150)
                .collect(Collectors.toList());

        // Print a few
        for (int i=0; i< 5; i++)
        {
            System.err.println(heavyApples.get(i));
        }
    }

    static class Apple
    {
        int weight;  // grams

        Apple(int weight){this.weight = weight;}

        public String toString(){return String.valueOf(weight);}
    }
}
