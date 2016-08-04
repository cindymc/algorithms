package algorithms.recursion;

/**
 * Created by cindymc on 8/4/16.
 */
public class BinarySearch
{
    /**
     * Choose an element at the mid-point of a SORTED list
     * Check whether it's smaller or greater than the number you're looking for
     * Reduce the search area to the portion where the element might be present
     * Recurse
     *
     * Base case: break when:
     *  - element at midpoint the one we're looking for
     *  - or there is no part of the list to search and we haven't found the element
     *
     * Recursive case:
     *  - binary search smaller portions of the list where the element might be found
     */

    /**
     * @param sortedArray
     * @param number
     * @param min
     * @param max
     * @return * -1 if min>max (so we haven't found the number)
     * * index of the number we're searching for if we found it
     * * recurse into binary search if not yet found.
     */
    public static int binarySearch(int[] sortedArray, int number, int min, int max)
    {
        // Base case: haven't found it
        if (min > max)
        {
            return -1;
        }

        // Find the midpoint index
        int mid = min + (max-min)/2;

        // Base case: found it
        if (sortedArray[mid] == number)
        {
            return mid;
        }

        // Otherwise, recurse
        if (sortedArray[mid] > number)
        {
            // search first half; don't forget to subtract 1 from mid index!
            return binarySearch(sortedArray, number, min, mid-1);
        }
        else
        {
            // search last half.  Note we've already compared sortedArray[mid] for number, so we can throw that out
            return binarySearch(sortedArray, number, mid+1, max);
        }
    }
}
