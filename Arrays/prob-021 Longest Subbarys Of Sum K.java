/*
 * ============================================================================
 *             LONGEST SUBARRAY WITH SUM K (PREFIX SUM + HASHMAP)
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
 * Given an array arr[] and an integer k,
 * find the length of the longest subarray
 * whose sum is equal to k.
 *
 * Example:
 *
 * Input:
 * arr = [10,5,2,7,1,9]
 * k = 15
 *
 * Output:
 * 4
 *
 * Explanation:
 *
 * The longest subarray is:
 *
 * [5,2,7,1]
 *
 * Sum = 15
 *
 * Length = 4
 *
 * ============================================================================
 * Intuition
 * ============================================================================
 *
 * A brute-force solution checks every possible subarray,
 * resulting in O(n²) time complexity.
 *
 * Instead,
 * use Prefix Sum and HashMap.
 *
 * Prefix Sum stores the sum of all elements
 * from index 0 to the current index.
 *
 * If:
 *
 * currentPrefixSum - previousPrefixSum = k
 *
 * then,
 *
 * the subarray between those indices
 * has sum equal to k.
 *
 * Therefore,
 * while traversing the array,
 * check whether (prefixSum - k)
 * already exists in the HashMap.
 *
 * ============================================================================
 */

import java.util.*;

class Solution {

    public int longestSubarray(int[] arr, int k) {

        // Stores:
        // Key   -> Prefix Sum
        // Value -> First occurrence index
        HashMap<Long, Integer> map = new HashMap<>();

        long preSum = 0;

        int maxLen = 0;

        // Traverse the array
        for (int i = 0; i < arr.length; i++) {

            // Calculate prefix sum
            preSum += arr[i];

            // If the prefix sum itself equals k,
            // then subarray starts from index 0
            if (preSum == k) {

                maxLen = Math.max(maxLen, i + 1);
            }

            // Check if (prefixSum - k) already exists
            if (map.containsKey(preSum - k)) {

                maxLen = Math.max(maxLen,
                        i - map.get(preSum - k));
            }

            // Store only the first occurrence
            // of every prefix sum
            if (!map.containsKey(preSum)) {

                map.put(preSum, i);
            }
        }

        return maxLen;
    }
}

/*
 * ============================================================================
 * Algorithm
 * ============================================================================
 *
 * Step 1:
 * Initialize
 *
 * preSum = 0
 *
 * HashMap
 *
 * maxLen = 0
 *
 * ---------------------------------------------------------
 *
 * Step 2:
 * Traverse the array.
 *
 * preSum += arr[i]
 *
 * ---------------------------------------------------------
 *
 * Step 3:
 *
 * If
 *
 * preSum == k
 *
 * update answer.
 *
 * ---------------------------------------------------------
 *
 * Step 4:
 *
 * Search:
 *
 * preSum - k
 *
 * If found,
 * update maximum length.
 *
 * ---------------------------------------------------------
 *
 * Step 5:
 *
 * Store the prefix sum
 * only if it is not already present.
 *
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Input:
 *
 * arr = [10,5,2,7,1,9]
 *
 * k = 15
 *
 * ---------------------------------------------------------
 *
 * i = 0
 *
 * preSum = 10
 *
 * Map:
 *
 * {10 → 0}
 *
 * ---------------------------------------------------------
 *
 * i = 1
 *
 * preSum = 15
 *
 * preSum == k
 *
 * Length = 2
 *
 * Store:
 *
 * {10→0,15→1}
 *
 * ---------------------------------------------------------
 *
 * i = 2
 *
 * preSum = 17
 *
 * Search:
 *
 * 17 - 15 = 2
 *
 * Not found.
 *
 * ---------------------------------------------------------
 *
 * i = 3
 *
 * preSum = 24
 *
 * Search:
 *
 * 24 - 15 = 9
 *
 * Not found.
 *
 * ---------------------------------------------------------
 *
 * i = 4
 *
 * preSum = 25
 *
 * Search:
 *
 * 25 - 15 = 10
 *
 * Found at index 0
 *
 * Length = 4 - 0 = 4
 *
 * Update answer.
 *
 * Final Answer = 4
 *
 * ============================================================================
 * Why Prefix Sum Works?
 * ============================================================================
 *
 * Suppose:
 *
 * Prefix Sum till index i = 25
 *
 * We need subarray sum = 15
 *
 * Then,
 *
 * Previous Prefix Sum must be:
 *
 * 25 - 15 = 10
 *
 * If 10 already exists,
 * the elements between those two indices
 * must sum to 15.
 *
 * ============================================================================
 * Why store only the first occurrence?
 * ============================================================================
 *
 * Example:
 *
 * arr = [2,0,0,3]
 *
 * k = 3
 *
 * Prefix Sums:
 *
 * 2
 * 2
 * 2
 * 5
 *
 * If we overwrite:
 *
 * 2 → 2
 *
 * Length:
 *
 * 3 - 2 = 1
 *
 * Wrong.
 *
 * If we keep the first occurrence:
 *
 * 2 → 0
 *
 * Length:
 *
 * 3 - 0 = 3
 *
 * Correct.
 *
 * Therefore,
 * always store the first occurrence
 * of every prefix sum.
 *
 * ============================================================================
 * Common Mistakes I Made
 * ============================================================================
 *
 * ❌ Used:
 *
 * if(!map.containsKey(preSum-k))
 *
 * map.put(preSum,i);
 *
 * Wrong.
 *
 * The HashMap stores prefix sums,
 * so the check must also use prefix sums.
 *
 * Correct:
 *
 * if(!map.containsKey(preSum))
 *
 * map.put(preSum,i);
 *
 * ---------------------------------------------------------
 *
 * ❌ Overwriting prefix sums.
 *
 * Doing:
 *
 * map.put(preSum,i);
 *
 * every time
 *
 * loses the earliest index,
 * producing smaller subarray lengths.
 *
 * ============================================================================
 * Interview Follow-up
 * ============================================================================
 *
 * Q1. Why use long for prefix sum?
 *
 * Because the sum of array elements
 * may exceed Integer.MAX_VALUE.
 *
 * Using long prevents overflow.
 *
 * ---------------------------------------------------------
 *
 * Q2. Can this be solved using Sliding Window?
 *
 * Only if all array elements are positive.
 *
 * If negative numbers are present,
 * Sliding Window does not work.
 *
 * Prefix Sum + HashMap works
 * for both positive and negative numbers.
 *
 * ============================================================================
 * Real-World Applications
 * ============================================================================
 *
 * 1. Financial Analysis
 * ---------------------
 * Finding the longest period where
 * the total income or expenditure
 * equals a target value.
 *
 * ---------------------------------------------------------
 *
 * 2. Website Analytics
 * --------------------
 * Finding the longest continuous period
 * where the total number of visitors
 * equals a required count.
 *
 * ---------------------------------------------------------
 *
 * 3. Sensor Data Analysis
 * -----------------------
 * Detecting the longest continuous interval
 * where accumulated readings
 * match a target value.
 *
 * ============================================================================
 * Pattern to Remember
 * ============================================================================
 *
 * Whenever the problem asks:
 *
 * "Longest Subarray with Sum K"
 *
 * Think:
 *
 * Prefix Sum
 *      ↓
 * HashMap
 *      ↓
 * Search (prefixSum - k)
 *      ↓
 * Update Answer
 *
 * Similar Problems:
 *
 * ✔ Longest Subarray with Sum K
 * ✔ Subarray Sum Equals K
 * ✔ Count Subarrays with Given Sum
 * ✔ Continuous Subarray Sum
 *
 * ============================================================================
 */
