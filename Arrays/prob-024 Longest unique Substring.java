/*
 * ============================================================================
 *              LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
 * ============================================================================
 * Difficulty : Medium
 *
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 *
 * ============================================================================
 * Problem Statement
 * ============================================================================
 *
 * Given a string s,
 * find the length of the longest substring
 * without repeating characters.
 *
 * A substring consists of contiguous characters.
 *
 * Example:
 *
 * Input:
 *
 * s = "abcabcbb"
 *
 * Output:
 *
 * 3
 *
 * Explanation:
 *
 * The longest substring without repeating characters is:
 *
 * "abc"
 *
 * Length = 3
 *
 * ============================================================================
 * Intuition
 * ============================================================================
 *
 * A brute-force solution checks every possible substring,
 * resulting in O(n²) time complexity.
 *
 * Instead,
 * use the Sliding Window technique.
 *
 * Maintain a window containing only unique characters.
 *
 * Whenever a duplicate character is found,
 * move the left pointer just after
 * the previous occurrence of that character.
 *
 * To quickly find the previous occurrence,
 * store the last index of every character
 * using an array.
 *
 * ============================================================================
 */

import java.util.*;

class Solution {

    public int lengthOfLongestSubstring(String s) {

        int n = s.length();

        int left = 0;
        int right = 0;

        int maxLength = 0;

        // Stores the last index of every character
        int[] hash = new int[256];

        // Initially no character has appeared
        Arrays.fill(hash, -1);

        while (right < n) {

            // Character already exists inside the current window
            if (hash[s.charAt(right)] >= left) {

                left = hash[s.charAt(right)] + 1;
            }

            // Calculate current window length
            int length = right - left + 1;

            maxLength = Math.max(maxLength, length);

            // Store latest index of current character
            hash[s.charAt(right)] = right;

            right++;
        }

        return maxLength;
    }
}

/*
 * ============================================================================
 * Algorithm
 * ============================================================================
 *
 * Step 1:
 *
 * Initialize:
 *
 * left = 0
 *
 * right = 0
 *
 * maxLength = 0
 *
 * hash[] = -1
 *
 * ---------------------------------------------------------------------------
 *
 * Step 2:
 *
 * Traverse the string using the right pointer.
 *
 * ---------------------------------------------------------------------------
 *
 * Step 3:
 *
 * If the current character
 * already exists inside the current window,
 * move the left pointer
 * to one position after its previous occurrence.
 *
 * ---------------------------------------------------------------------------
 *
 * Step 4:
 *
 * Calculate the current window length.
 *
 * Update the maximum length.
 *
 * ---------------------------------------------------------------------------
 *
 * Step 5:
 *
 * Store the latest index
 * of the current character.
 *
 * Continue until the string ends.
 *
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Input:
 *
 * s = "abcabcbb"
 *
 * ---------------------------------------------------------------------------
 *
 * left = 0
 *
 * right = 0
 *
 * Window:
 *
 * "a"
 *
 * Length = 1
 *
 * ---------------------------------------------------------------------------
 *
 * right = 1
 *
 * Window:
 *
 * "ab"
 *
 * Length = 2
 *
 * ---------------------------------------------------------------------------
 *
 * right = 2
 *
 * Window:
 *
 * "abc"
 *
 * Length = 3
 *
 * ---------------------------------------------------------------------------
 *
 * right = 3
 *
 * Character = 'a'
 *
 * Previous index = 0
 *
 * Move left:
 *
 * left = 1
 *
 * Window:
 *
 * "bca"
 *
 * Length = 3
 *
 * ---------------------------------------------------------------------------
 *
 * Continue...
 *
 * Maximum Length = 3
 *
 * Final Answer = 3
 *
 * ============================================================================
 * Why do we store the last index?
 * ============================================================================
 *
 * Suppose:
 *
 * s = "abba"
 *
 * Index:
 *
 * 0 1 2 3
 * a b b a
 *
 * When we reach the second 'b',
 * we must know where
 * the previous 'b' occurred.
 *
 * Previous 'b' is at index 1.
 *
 * Therefore,
 *
 * left = 1 + 1 = 2
 *
 * The new window becomes:
 *
 * "ba"
 *
 * If we only stored:
 *
 * b → 1
 *
 * (meaning "seen")
 *
 * we would not know
 * where the duplicate occurred.
 *
 * Therefore,
 * storing only 1 is not enough.
 *
 * We must store:
 *
 * Character → Last Index
 *
 * ============================================================================
 * Why not store only 1?
 * ============================================================================
 *
 * Wrong:
 *
 * hash[ch] = 1;
 *
 * Example:
 *
 * s = "abc"
 *
 * Hash becomes:
 *
 * a → 1
 * b → 1
 * c → 1
 *
 * All characters have the same value.
 *
 * We lose their positions.
 *
 * Correct:
 *
 * hash[ch] = right;
 *
 * Example:
 *
 * a → 0
 * b → 1
 * c → 2
 *
 * Now we know exactly
 * where each character last appeared.
 *
 * ============================================================================
 * Common Mistakes I Made
 * ============================================================================
 *
 * ❌ Restarted the HashSet whenever a duplicate appeared.
 *
 * This misses valid substrings like:
 *
 * "dvdf"
 *
 * Correct answer:
 *
 * "vdf"
 *
 * Length = 3
 *
 * ---------------------------------------------------------------------------
 *
 * ❌ Stored:
 *
 * hash[ch] = 1;
 *
 * Wrong because
 * we only know the character exists,
 * not where it appeared.
 *
 * Correct:
 *
 * hash[ch] = right;
 *
 * ---------------------------------------------------------------------------
 *
 * ❌ Forgot to initialize:
 *
 * Arrays.fill(hash,-1);
 *
 * Since the default value is 0,
 * index 0 and "not present"
 * become indistinguishable.
 *
 * ============================================================================
 * Interview Follow-up
 * ============================================================================
 *
 * Q1. Why use an array instead of HashMap?
 *
 * For ASCII characters,
 * array lookup is faster (O(1))
 * than HashMap lookup.
 *
 * ---------------------------------------------------------------------------
 *
 * Q2. Why store the last index?
 *
 * Because when a duplicate is found,
 * we need to move the left pointer
 * exactly after the previous occurrence.
 *
 * ---------------------------------------------------------------------------
 *
 * Q3. Why Sliding Window?
 *
 * Because every character
 * enters and leaves the window
 * at most once,
 * giving O(n) time complexity.
 *
 * ============================================================================
 * Real-World Applications
 * ============================================================================
 *
 * 1. Text Processing
 * ------------------
 * Finding the longest sequence
 * of unique characters in a document.
 *
 * ---------------------------------------------------------------------------
 *
 * 2. Network Packet Analysis
 * --------------------------
 * Detecting the longest stream
 * without repeated packet identifiers.
 *
 * ---------------------------------------------------------------------------
 *
 * 3. Data Compression
 * -------------------
 * Identifying unique character sequences
 * used in compression algorithms.
 *
 * ============================================================================
 * Pattern to Remember
 * ============================================================================
 *
 * Whenever the problem asks:
 *
 * ✔ Longest Substring
 * ✔ Unique Characters
 * ✔ No Repeating Characters
 *
 * Think:
 *
 * Sliding Window
 *        ↓
 * Two Pointers
 *        ↓
 * Store Last Index
 *        ↓
 * Move Left Pointer
 *        ↓
 * Update Maximum Length
 *
 * Similar Problems:
 *
 * ✔ Longest Substring Without Repeating Characters
 * ✔ Longest Repeating Character Replacement
 * ✔ Minimum Window Substring
 * ✔ Longest Substring with At Most K Distinct Characters
 *
 * ============================================================================
 */
```
