/*
 * ============================================================================
 *                    SUBARRAY SUM EQUALS K (PREFIX SUM + HASHMAP)
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
 * Given an integer array nums and an integer k,
 * return the total number of continuous subarrays
 * whose sum equals k.
 *
 * Example:
 *
 * Input:
 * nums = [1,1,1]
 * k = 2
 *
 * Output:
 * 2
 *
 * Explanation:
 *
 * Subarrays are:
 *
 * [1,1] (index 0-1)
 * [1,1] (index 1-2)
 *
 * Therefore,
 * answer = 2.
 *
 * ============================================================================
 * Intuition
 * ============================================================================
 *
 * A brute-force solution checks every possible subarray,
 * resulting in O(n²) time complexity.
 *
 * Instead,
 * use Prefix Sum + HashMap.
 *
 * Prefix Sum stores the cumulative sum from
 * index 0 to the current index.
 *
 * If:
 *
 * currentPrefixSum - previousPrefixSum = k
 *
 * then,
 *
 * the subarray between those two indices
 * has sum equal to k.
 *
 * Unlike "Longest Subarray with Sum K",
 * here we need the TOTAL NUMBER of subarrays.
 *
 * Therefore,
 * the HashMap stores the frequency
 * of every prefix sum.
 *
 * ============================================================================
 */

import java.util.*;

class Solution {

    public int subarraySum(int[] nums, int k) {

        // Key   -> Prefix Sum
        // Value -> Frequency of Prefix Sum
        HashMap<Long, Integer> map = new HashMap<>();

        long preSum = 0;
        int count = 0;

        // Prefix sum 0 occurs once before the array starts
        map.put(0L, 1);

        // Traverse the array
        for (int i = 0; i < nums.length; i++) {

            // Calculate prefix sum
            preSum += nums[i];

            // If (preSum - k) exists,
            // all its occurrences form valid subarrays
            if (map.containsKey(preSum - k)) {
                count += map.get(preSum - k);
            }

            // Increase the frequency of current prefix sum
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }

        return count;
    }
}

/*
 * ============================================================================
 * Algorithm
 * ============================================================================
 *
 * Step 1:
 * Initialize:
 *
 * preSum = 0
 *
 * count = 0
 *
 * HashMap
 *
 * Store:
 *
 * map.put(0,1)
 *
 * ---------------------------------------------------------
 *
 * Step 2:
 * Traverse the array.
 *
 * preSum += nums[i]
 *
 * ---------------------------------------------------------
 *
 * Step 3:
 *
 * Search:
 *
 * preSum - k
 *
 * If found,
 * add its frequency to the answer.
 *
 * ---------------------------------------------------------
 *
 * Step 4:
 *
 * Increase the frequency
 * of the current prefix sum.
 *
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Input:
 *
 * nums = [1,1,1]
 *
 * k = 2
 *
 * Initially
 *
 * Map:
 *
 * {0 → 1}
 *
 * ---------------------------------------------------------
 *
 * i = 0
 *
 * preSum = 1
 *
 * Search:
 *
 * 1 - 2 = -1
 *
 * Not found.
 *
 * Store:
 *
 * {0→1,1→1}
 *
 * ---------------------------------------------------------
 *
 * i = 1
 *
 * preSum = 2
 *
 * Search:
 *
 * 2 - 2 = 0
 *
 * Found.
 *
 * count = 1
 *
 * Store:
 *
 * {0→1,1→1,2→1}
 *
 * ---------------------------------------------------------
 *
 * i = 2
 *
 * preSum = 3
 *
 * Search:
 *
 * 3 - 2 = 1
 *
 * Found.
 *
 * count = 2
 *
 * Final Answer = 2
 *
 * ============================================================================
 * Why map.put(0,1)?
 * ============================================================================
 *
 * Before the array starts,
 * imagine there is a prefix sum of 0.
 *
 * Example:
 *
 * nums = [3]
 *
 * k = 3
 *
 * Prefix Sum:
 *
 * 3
 *
 * We search:
 *
 * 3 - 3 = 0
 *
 * Since 0 already exists once,
 * we correctly count the subarray
 * starting from index 0.
 *
 * Therefore,
 *
 * map.put(0,1)
 *
 * automatically handles
 * subarrays beginning at index 0.
 *
 * ============================================================================
 * Why don't we write:
 *
 * if(preSum == k)
 *      count++;
 *
 * ============================================================================
 *
 * Because:
 *
 * map.put(0,1)
 *
 * already handles this case.
 *
 * Example:
 *
 * nums = [3]
 *
 * k = 3
 *
 * preSum = 3
 *
 * Search:
 *
 * 3 - 3 = 0
 *
 * Map contains:
 *
 * 0 → 1
 *
 * So,
 *
 * count += 1
 *
 * gives the correct answer.
 *
 * If we also write:
 *
 * if(preSum == k)
 *      count++;
 *
 * then the same subarray
 * gets counted twice.
 *
 * ============================================================================
 * Difference from "Longest Subarray with Sum K"
 * ============================================================================
 *
 * Longest Subarray:
 *
 * HashMap stores:
 *
 * Prefix Sum → First Index
 *
 * Because we need the maximum length.
 *
 * ---------------------------------------------------------
 *
 * Subarray Sum Equals K:
 *
 * HashMap stores:
 *
 * Prefix Sum → Frequency
 *
 * Because we need the total number
 * of valid subarrays.
 *
 * ============================================================================
 * Common Mistakes I Made
 * ============================================================================
 *
 * ❌ Stored index instead of frequency.
 *
 * Wrong:
 *
 * map.put(preSum, i);
 *
 * Correct:
 *
 * map.put(preSum,
 *         map.getOrDefault(preSum,0)+1);
 *
 * ---------------------------------------------------------
 *
 * ❌ Wrote:
 *
 * count++;
 *
 * whenever (preSum-k) existed.
 *
 * Wrong because
 * one prefix sum can appear
 * multiple times.
 *
 * Correct:
 *
 * count += map.get(preSum-k);
 *
 * ---------------------------------------------------------
 *
 * ❌ Used:
 *
 * if(preSum == k)
 *
 * along with
 *
 * map.put(0,1)
 *
 * causing duplicate counting.
 *
 * ============================================================================
 * Interview Follow-up
 * ============================================================================
 *
 * Q1. Why use HashMap?
 *
 * Because searching a prefix sum
 * takes O(1) average time.
 *
 * ---------------------------------------------------------
 *
 * Q2. Why use long instead of int?
 *
 * Prefix sums may exceed
 * Integer.MAX_VALUE.
 *
 * Using long prevents overflow.
 *
 * ---------------------------------------------------------
 *
 * Q3. Can Sliding Window solve this?
 *
 * Only if all numbers are positive.
 *
 * Since negative numbers are allowed,
 * Sliding Window fails.
 *
 * Prefix Sum + HashMap works
 * for all cases.
 *
 * ============================================================================
 * Real-World Applications
 * ============================================================================
 *
 * 1. Banking & Finance
 * --------------------
 * Counting the number of continuous
 * transactions whose total amount
 * equals a target value.
 *
 * ---------------------------------------------------------
 *
 * 2. Website Analytics
 * --------------------
 * Counting the number of continuous
 * time intervals where user activity
 * equals a required threshold.
 *
 * ---------------------------------------------------------
 *
 * 3. Sensor Data Processing
 * -------------------------
 * Finding the number of continuous
 * readings whose cumulative value
 * matches a target.
 *
 * ============================================================================
 * Pattern to Remember
 * ============================================================================
 *
 * Whenever the problem asks:
 *
 * "Count Subarrays with Sum K"
 *
 * Think:
 *
 * Prefix Sum
 *      ↓
 * HashMap (Frequency)
 *      ↓
 * Search (preSum - k)
 *      ↓
 * Add Frequency
 *
 * Similar Problems:
 *
 * ✔ Subarray Sum Equals K
 * ✔ Count Binary Subarrays with Sum
 * ✔ Continuous Subarray Sum
 * ✔ Prefix Sum Based Counting Problems
 *
 * ============================================================================
 */
