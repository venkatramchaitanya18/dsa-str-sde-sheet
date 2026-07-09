/*
 * ============================================================================
 *                               TWO SUM
 * ============================================================================
 * Difficulty : Easy
 *
 * Time Complexity  : O(n)
 * Space Complexity : O(n)
 *
 * ============================================================================
 * Problem Statement
 * ============================================================================
 *
 * Given an integer array nums and an integer target,
 * return the indices of the two numbers such that
 * they add up to the target.
 *
 * You may assume that exactly one solution exists,
 * and you may not use the same element twice.
 *
 * Example:
 *
 * Input:
 * nums = [2,7,11,15]
 * target = 9
 *
 * Output:
 * [0,1]
 *
 * Explanation:
 * nums[0] + nums[1] = 2 + 7 = 9
 *
 * ============================================================================
 * Intuition
 * ============================================================================
 *
 * A brute-force solution checks every pair of elements,
 * which takes O(n²) time.
 *
 * Instead, we use a HashMap to store:
 *
 * Key   -> Number
 * Value -> Index
 *
 * For every element,
 * calculate the required number:
 *
 * required = target - currentElement
 *
 * If the required number already exists in the HashMap,
 * we have found the answer.
 *
 * Otherwise,
 * store the current element in the HashMap.
 *
 * ============================================================================
 */

import java.util.HashMap;

class Solution {

    public int[] twoSum(int[] nums, int target) {

        // Stores number as key and its index as value
        HashMap<Integer, Integer> map = new HashMap<>();

        // Traverse the array
        for (int i = 0; i < nums.length; i++) {

            // Calculate the required number
            int need = target - nums[i];

            // Check whether the required number already exists
            if (map.containsKey(need)) {

                // Return both indices
                return new int[]{map.get(need), i};
            }

            // Store current number and its index
            map.put(nums[i], i);
        }

        return new int[]{};
    }
}

/*
 * ============================================================================
 * Algorithm
 * ============================================================================
 *
 * Step 1:
 * Create an empty HashMap.
 *
 * Key   -> Number
 * Value -> Index
 *
 * ---------------------------------------------------------------------------
 *
 * Step 2:
 * Traverse the array.
 *
 * For every element,
 *
 * required = target - nums[i]
 *
 * ---------------------------------------------------------------------------
 *
 * Step 3:
 * Check whether the required number
 * already exists in the HashMap.
 *
 * If yes,
 * return both indices.
 *
 * ---------------------------------------------------------------------------
 *
 * Step 4:
 * Otherwise,
 * store the current number and its index.
 *
 * map.put(nums[i], i)
 *
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Input:
 *
 * nums = [2,7,11,15]
 *
 * target = 9
 *
 * ---------------------------------------------------------------------------
 *
 * i = 0
 *
 * Current = 2
 *
 * Need = 7
 *
 * HashMap = {}
 *
 * 7 not found.
 *
 * Store:
 *
 * 2 -> 0
 *
 * ---------------------------------------------------------------------------
 *
 * i = 1
 *
 * Current = 7
 *
 * Need = 2
 *
 * HashMap:
 *
 * 2 -> 0
 *
 * 2 found.
 *
 * Return:
 *
 * [0,1]
 *
 * ============================================================================
 * Why HashMap?
 * ============================================================================
 *
 * HashMap provides O(1) average time complexity
 * for searching and inserting elements.
 *
 * Instead of searching the entire array every time,
 * we directly check whether the required number exists.
 *
 * ============================================================================
 * Common Mistakes I Made
 * ============================================================================
 *
 * ❌ Used:
 *
 * map.contains(...)
 *
 * Correct:
 *
 * map.containsKey(...)
 *
 * ---------------------------------------------------------------------------
 *
 * ❌ Used:
 *
 * map.set(...)
 *
 * Correct:
 *
 * map.put(...)
 *
 * ---------------------------------------------------------------------------
 *
 * ❌ Returned:
 *
 * map.get(i)
 *
 * Correct:
 *
 * map.get(target - nums[i])
 *
 * Because the HashMap stores:
 *
 * Key   -> Number
 * Value -> Index
 *
 * ============================================================================
 * Interview Follow-up
 * ============================================================================
 *
 * Q1. Can we solve this without extra space?
 *
 * Yes.
 *
 * Sort the array and use the Two Pointer approach.
 *
 * Time  : O(n log n)
 *
 * Space : O(1)
 *
 * But,
 * sorting changes the original indices,
 * so we need extra work to keep track of them.
 *
 * ---------------------------------------------------------------------------
 *
 * Q2. Why is the HashMap solution preferred?
 *
 * Because it gives the optimal time complexity:
 *
 * O(n)
 *
 * ============================================================================
 * Real-World Applications
 * ============================================================================
 *
 * 1. Financial Applications
 * -------------------------
 * Find two transactions whose sum equals
 * a target amount.
 *
 * ---------------------------------------------------------------------------
 *
 * 2. E-Commerce Systems
 * ---------------------
 * Find two product prices whose total
 * matches the customer's budget.
 *
 * ---------------------------------------------------------------------------
 *
 * 3. Data Processing
 * ------------------
 * Used whenever we need fast lookup
 * while processing large datasets.
 *
 * ============================================================================
 * Pattern to Remember
 * ============================================================================
 *
 * Whenever you need:
 *
 * "Find two numbers satisfying a condition"
 *
 * Think:
 *
 * ✔ HashMap
 *
 * Store:
 *
 * Key   -> Value
 *
 * Search:
 *
 * target - currentElement
 *
 * ============================================================================
 */
