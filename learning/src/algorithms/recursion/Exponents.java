package algorithms.recursion;

/**
 * Created by cindymc on 8/5/16.
 */
public class Exponents
{
    public static void main(String [] args)
    {
        System.err.println("2^3 = " + power(2,3));
    }

    static int power(int base, int exponent)
    {
        // Base case
        if (exponent == 0)
        {
            return 1;
        }

        // Otherwise, recurse
        int subproblem = power(base, exponent-1);

        // If we've already recursed the method, then we'll have the value for 'subproblem'
        return base * subproblem;
    }
}
