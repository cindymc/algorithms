package algorithms.recursion;

/**
 * Created by cindymc on 8/5/16.
 */
public class ReverseString
{
    public static void main(String [] args)
    {
        String word = "badminton";
        String r = reverse(word);
        System.err.println(word + " reversed: " + r);
    }

    static String reverse(String s)
    {
        // Base case: the null string or a string of 1 char
        if (s==null || s.length() <=1)
        {
            return s;
        }

        // Otherwise recurse.  Take the first character and append it to the end of the current String
        char firstChar = s.charAt(0);
        String restOfString = s.substring(1);
        return reverse(restOfString) + firstChar;
    }
}
