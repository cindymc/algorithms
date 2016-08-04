package algorithms.recursion;

import java.util.List;

/**
 * Find all anagrams of a given word.  Doesn't have to be a real word, just permutations of the letters.
 *
 * If all letters are unique, there are N! anagrams
 *
 * Algorithm:
 *  - break the word into smaller and smaller words, until we have anagram of the smallest word
 *  - to the smaller anagrams, add in letter by letter at every possible position
 *  - recursively do this until we get all the letters in at every position
 *
 * Base case:
 *  - the anagram is an empty string
 *  - the smallest word of one letter is the anagram itself
 *
 * Recursive case
 *  - find anagrams of smaller words by removing letters from the original word
 *  - add the letters back one by one to every position
 *
 *  Complexity is O(N!)
 *
 * Created by cindymc on 8/4/16.
 */
public class Anagrams
{
    // Helper method
    private static void insertCharacterAtEveryIndex(List<String> potentialAnagrams,
                                                    char currentChar,
                                                    List<String> anagramList)
    {
        for (String potentialAnagram : potentialAnagrams)
        {
            for (int index = 0; index <= s.l)
        }
    }
}
