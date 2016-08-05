package algorithms.recursion;

import java.io.File;
import java.util.Arrays;

/**
 * Created by cindymc on 7/7/16.
 */
public class RecursionExamples
{
    public static void main(String [] args)
    {
        // Reverse Ints
       // int [] x = new int[]{1,2,3,4,5};
        //reverseInts(x,0, x.length-1);

        //String r = reverseString("hello");
        //System.err.println("Reversed final: " + r);

        //System.err.println("Factorial of 6: " + factorial(6));
       // int [] data = new int[]{10,13,15,17,21,38,47,53,78};
       // boolean found = binarySearch(data, 18, 0, data.length-1);
       // System.err.println("Found it " + found);

        int [] data = new int[]{32,54,76,89,100,3,15,33,98,76,45,26};
        mergeSort(data);
        System.err.println(Arrays.toString(data));
    }

    /**
     * Merge sort
     */
    public static void mergeSort(int [] s)
    {
        int n = s.length;
        if (n<2)
        {
            return;
        }

        // Divide
        int mid = n/2;
        int [] s1 = Arrays.copyOfRange(s,0,mid);
        int [] s2 = Arrays.copyOfRange(s,mid,n);

        // Conquer
        mergeSort(s1);
        mergeSort(s2);

        // Merge results
        merge(s1,s2,s);
    }


    private static void merge(int[] s1, int[] s2, int[] s)
    {
        int i=0, j=0;
        while(i+j < s.length)
        {
            if (j == s2.length || (i < s1.length && s1[i] < s2[j]))
            {
                s[i+j] = s1[i++];
            }
            else
            {
                s[i+j] = s2[j++];
            }
        }

        // Could also do this
        // The last entry is the NUMBER of components copied, not an index into the array
       // System.arraycopy(s1, 0, s, 0, s1.length);
        //System.arraycopy(s2, 0, s, s1.length, s2.length);

    }

    /**
     * Disk usage
     */
    public static long diskUsage(File root)
    {
        long total = root.length();
        if (root.isDirectory())
        {
            for (String childname : root.list()) {
                File child = new File(root, childname);
                total += diskUsage(child);
            }
        }
        System.err.println(total + "\t" + root);
        return total;
    }

    /**
     * Binary search assumes the array is SORTED and INDEX-ABLE.  It is O(logn)
     * @param data input array
     * @param target find this number
     * @param low low index of array
     * @param high high index of array
     */
    static boolean binarySearch(int [] data, int target, int low, int high)
    {
        // Stop when pointers are equal or cross
        if (low > high)
        {
            return false;
        }
        else
        {
            int mid = (low+high)/2;  // find midpoint

            // If this is target, return
            if (target == data[mid])
            {
                return true;
            }
            // Otherwise, partition the data in half and recursively search that
            else if (target < data[mid])
            {
                return binarySearch(data, target, low, mid-1);
            }
            else
            {
                return binarySearch(data, target, mid+1, high);
            }
        }
    }

    // Reverse a String
    static String reverseString(String s)
    {
        // When we're at the final character of the string, return that character
        if ((s == null) || (s.length()<=1))
        {
            return s;
        }
        // otherwise, call reverse on the remainder of the string, then add the first character to the end
        // reverse("o") returns 'o'
        // reverse("lo")  adds 'l' to 'o', returns 'ol'
        // reverse("llo") adds 'l' to 'ol', returns 'oll'
        // reverse("ello") adds 'e' to 'oll', returns 'olle'
        // reverst("hello") adds 'h' to 'olle', returns 'olleh'
        return reverseString(s.substring(1)) + s.charAt(0);
    }

    /**
     * When the execution of a method leads to a nested method call, the execution of the former call is suspended and
     * its frame stores the place in the source code at which the flow of control should continue upon return of the
     * nested call. A new frame is then created for the nested method call. This process is used both in the standard
     * case of one method calling a different method, or in the recursive case where a method invokes itself. The key
     * point is to have a separate frame for each active call.
     */
    static int factorial(int n)
    {
        if (n==0)
        {
            return 1;
        }
        return n * factorial(n-1);
    }

    // Reverse an array of ints
    static void reverseInts(int []x, int i, int j)
    {
        // Do until pointers of end and beginning of array match
        if (i<j)
        {
            System.err.println(i + "\t" + j + "\t" + Arrays.toString(x));

            // swap
            int tmp = x[i];
            x[i] = x[j];
            x[j] = tmp;

            // recurse.  Increment front of array and decrement end of array
            reverseInts(x, ++i, --j);
        }
    }


}
