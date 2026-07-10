/*
 * ============================================================================
 *                     LONGEST CONSECUTIVE SEQUENCE
 * ============================================================================
 * Difficulty : Medium
 *
 * Time Complexity  : O(n)
 * Space Complexity : O(n)
 *
 * ============================================================================
 * Problem Statement
 * ============================================================================
 *
 * Given an unsorted array of integers nums,
 * return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 * Example:
 *
 * Input:
 * nums = [100,4,200,1,3,2]
 *
 * Output:
 * 4
 *
 * Explanation:
 *
 * The longest consecutive sequence is:
 *
 * 1 → 2 → 3 → 4
 *
 * Length = 4
 *
 * ============================================================================
 * Intuition
 * ============================================================================
 *
 * A brute-force solution checks every number repeatedly,
 * resulting in O(n²) time complexity.
 *
 * Instead,
 * store all numbers inside a HashSet.
 *
 * HashSet provides O(1) average lookup time.
 *
 * We only start counting when the current number
 * is the beginning of a sequence.
 *
 * A number is the beginning of a sequence if
 * its previous number does NOT exist.
 *
 * Example:
 *
 * Sequence:
 *
 * 1 → 2 → 3 → 4
 *
 * Start counting only from 1.
 *
 * Skip 2, 3 and 4 because they already belong
 * to the same sequence.
 *
 * ============================================================================
 */

import java.util.*;

class Solution {

    public int longestConsecutive(int[] nums) {

        // Store all numbers in a HashSet
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int maxCount = 0;

        // Traverse all unique numbers
        for (int num : set) {

            // Start only if this number is the beginning of a sequence
            if (!set.contains(num - 1)) {

                int current = num;
                int count = 1;

                // Count consecutive numbers
                while (set.contains(current + 1)) {

                    current++;
                    count++;
                }

                // Update maximum sequence length
                maxCount = Math.max(maxCount, count);
            }
        }

        return maxCount;
    }
}

/*
 * ============================================================================
 * Algorithm
 * ============================================================================
 *
 * Step 1:
 * Store every number in a HashSet.
 *
 * ---------------------------------------------------------------------------
 *
 * Step 2:
 * Traverse every number in the HashSet.
 *
 * ---------------------------------------------------------------------------
 *
 * Step 3:
 * Check whether the previous number exists.
 *
 * if(!set.contains(num-1))
 *
 * If it doesn't exist,
 * this number is the start of a sequence.
 *
 * ---------------------------------------------------------------------------
 *
 * Step 4:
 * Continue checking:
 *
 * num+1
 * num+2
 * num+3
 * ...
 *
 * Count the length of the sequence.
 *
 * ---------------------------------------------------------------------------
 *
 * Step 5:
 * Update the maximum sequence length.
 *
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Input:
 *
 * nums = [100,4,200,1,3,2]
 *
 * HashSet:
 *
 * {100,4,200,1,3,2}
 *
 * ----------------------------------------------------
 *
 * num = 100
 *
 * 99 not present
 *
 * Start sequence
 *
 * 100
 *
 * Length = 1
 *
 * ----------------------------------------------------
 *
 * num = 4
 *
 * 3 exists
 *
 * Skip
 *
 * ----------------------------------------------------
 *
 * num = 200
 *
 * 199 not present
 *
 * Start sequence
 *
 * Length = 1
 *
 * ----------------------------------------------------
 *
 * num = 1
 *
 * 0 not present
 *
 * Start sequence
 *
 * 1
 * 2
 * 3
 * 4
 *
 * Length = 4
 *
 * maxCount = 4
 *
 * Final Answer = 4
 *
 * ============================================================================
 * Why check !set.contains(num-1)?
 * ============================================================================
 *
 * Example:
 *
 * nums =
 *
 * [1,2,3,4]
 *
 * Without checking:
 *
 * Start from 1 → Count = 4
 *
 * Start from 2 → Count = 3
 *
 * Start from 3 → Count = 2
 *
 * Start from 4 → Count = 1
 *
 * The same sequence is counted multiple times.
 *
 * ----------------------------------------------------
 *
 * With the check:
 *
 * 1 → Start
 *
 * 2 → Skip
 *
 * 3 → Skip
 *
 * 4 → Skip
 *
 * Every sequence is counted exactly once.
 *
 * ============================================================================
 * Common Mistakes I Made
 * ============================================================================
 *
 * ❌ Forgot to reset count for every sequence.
 *
 * Result:
 * Count kept increasing across different sequences.
 *
 * ----------------------------------------------------
 *
 * ❌ Wrote:
 *
 * while(set.contains(k+1) && !set.contains(k-1))
 *
 * Wrong because after k increases,
 * k-1 starts existing and the loop stops early.
 *
 * Correct:
 *
 * Check:
 *
 * !set.contains(num-1)
 *
 * only once BEFORE entering the while loop.
 *
 * ----------------------------------------------------
 *
 * ❌ Used:
 *
 * set.!contains(...)
 *
 * Correct syntax:
 *
 * !set.contains(...)
 *
 * ============================================================================
 * Interview Follow-up
 * ============================================================================
 *
 * Q1. Why use HashSet?
 *
 * Because searching inside a HashSet
 * takes O(1) average time.
 *
 * ----------------------------------------------------
 *
 * Q2. Why don't we sort the array?
 *
 * Sorting takes O(n log n),
 * but the problem requires O(n).
 *
 * HashSet allows us to solve it in linear time.
 *
 * ============================================================================
 * Real-World Applications
 * ============================================================================
 *
 * 1. Attendance Tracking
 * ----------------------
 * Finding the longest streak of consecutive
 * attendance days for a student or employee.
 *
 * ----------------------------------------------------
 *
 * 2. User Activity Analytics
 * --------------------------
 * Measuring the longest continuous login
 * or app usage streak of a user.
 *
 * ----------------------------------------------------
 *
 * 3. Inventory & Event Analysis
 * -----------------------------
 * Detecting the longest sequence of consecutive
 * product IDs, order IDs, or event timestamps.
 *
 * ============================================================================
 * Pattern to Remember
 * ============================================================================
 *
 * Whenever you see:
 *
 * "Longest Consecutive Sequence"
 *
 * Think:
 *
 * HashSet
 *      ↓
 * Find Start of Sequence
 *      ↓
 * Expand using While Loop
 *      ↓
 * Update Maximum Length
 *
 * Similar Problems:
 *
 * ✔ Longest Consecutive Sequence
 * ✔ Longest Increasing Consecutive Subsequence
 * ✔ Longest Arithmetic Sequence (Different Technique)
 * ✔ Streak / Consecutive Days Problems
 *
 * ============================================================================
 */
