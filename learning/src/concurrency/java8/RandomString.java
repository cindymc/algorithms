package concurrency.java8;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by cindymc on 8/4/16.
 */
public class RandomString
{
    public static void main(String [] args)
    {
        Random random = new Random();
        IntStream.range(0,10)
                .forEach(i -> System.out.println(generateRandomString(random, 9)));
    }

    public static String generateRandomString(Random random, int length)
    {
        return random.ints(48,122)   // unicode from 0 to z
                .filter(i -> (i<57 || i>65) && (i<90 || i>97))
                .mapToObj(i -> (char)i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
