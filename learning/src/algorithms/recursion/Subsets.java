package algorithms.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find all subsets of a given set.  For example, {1,2,3,4} subsets are {1,2}, {1}, {2,3} and so on, INCLUDING the
 * empty set {} and the original set {1,2,3,4}.
 *
 * If we have N elements in a set, the number of subsets will be 2^N.  For {1,2} it's 2^2 => {},{1},{2},{1,2}
 * So the complexity of this code is O(2^N)
 *
 * Algorithm:
 *  - create subsets of very small sets first
 *  - then for every subset, add new element in to create newer subsets
 *  - repeat until all elements from the original set have been used
 *
 * Base case:
 *  - when the original set is empty, only the empty set is its subset
 *
 * Recursive case:
 *  - add elements to the existing subsets to create new subsets
 *
 * Created by cindymc on 8/4/16.
 */
public class Subsets
{
    public static void main(String [] args)
    {
        List<List<Integer>> list = new ArrayList<>();

        // Can't use Arrays.asList here because it doesn't support remove() operation below
        // TODO: are there new range() methods to make this easier?
        List<Integer> set = new ArrayList<>();
        set.add(1);
        set.add(2);
        //set.add(3);

        subset(list, set);

        System.err.println("Results: " + list);
    }

    /**
     * Recursive algorithm to find all subsets of input list
     * @param subsetList holds subsets of the original superset
     * @param numberList  the original set
     */
    public static void subset(List<List<Integer>> subsetList, List<Integer> numberList)
    {
        // Base case: the empty set
        if (numberList.isEmpty())
        {
            // Empty set
            subsetList.add(new ArrayList<>());
            return;  // we are done
        }

        // Get the first element in the set and remove it; we will find subsets of all remaining numbers
        int currentNum = numberList.get(0);
        numberList.remove(0);

        // Recurse with the subset (input set - 1).  (We are pushing the subset onto the stack to evaluate later)
        subset(subsetList, numberList);

        // Now create a new subset from the original set, adding the current number back in
        List<List<Integer>> iteratingList = new ArrayList<>();
        iteratingList.addAll(subsetList);

        for (List<Integer> subset : iteratingList)
        {
            List<Integer> newSubset = new ArrayList<>();

            newSubset.addAll(subset);
            newSubset.add(currentNum);

            subsetList.add(newSubset);
        }
    }
}
