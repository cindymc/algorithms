package algorithms.recursion;

import java.util.ArrayList;
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
    public static void main(String [] args)
    {
        String word = "bar";
        System.err.println("Anagrams for 'bar': " + findAnagrams(word));
    }

    // Main algorithm
    static List<String> findAnagrams(String word)
    {
        if (word.length() == 1)  // base case
        {
            List<String> potentialAnagrams = new ArrayList<>();
            potentialAnagrams.add(word);
            return potentialAnagrams;
        }

        // Remove one character at a time from the original word and find the anagram of the REST of the word
        List<String> anagramList = new ArrayList<>();
        char currentChar = word.charAt(0);
        String subset = word.substring(1, word.length());

        // Recurse
        List<String> potentialList = findAnagrams(subset);
        insertCharacterAtEveryIndex(potentialList, currentChar, anagramList);

        return anagramList;
    }

    // Helper method
    private static void insertCharacterAtEveryIndex(List<String> potentialAnagrams,   // smaller words that will be built into anagrams
                                                    char currentChar,
                                                    List<String> anagramList)  // store results
    {
        for (String s : potentialAnagrams)
        {
            for (int index = 0; index <= s.length(); index++)
            {
                StringBuilder sb = new StringBuilder(s);
                if (index < s.length())
                {
                    sb.insert(index, currentChar);  // Insert at every position, including very last one
                }
                else
                {
                    sb.append(currentChar);
                }

                // Add the string with the letter added in to our list of anagrams
                anagramList.add(sb.toString());
            }
        }
    }
}
