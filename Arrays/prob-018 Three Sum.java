/*
 * ============================================================================
 *                                 3SUM
 * ============================================================================
 * Difficulty : Medium
 *
 * Time Complexity  : O(n²)
 * Space Complexity : O(1) (Ignoring the output list)
 *
 * ============================================================================
 * Problem Statement
 * ============================================================================
 *
 * Given an integer array nums,
 * return all unique triplets [nums[i], nums[j], nums[k]]
 * such that:
 *
 *      i != j
 *      i != k
 *      j != k
 *
 * and
 *
 *      nums[i] + nums[j] + nums[k] == 0
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Input:
 *
 * nums = [-1,0,1,2,-1,-4]
 *
 * Output:
 *
 * [[-1,-1,2],[-1,0,1]]
 *
 * ============================================================================
 * Intuition
 * ============================================================================
 *
 * A brute-force solution checks every possible triplet,
 * resulting in O(n³) time complexity.
 *
 * Instead,
 * we first sort the array.
 *
 * Then,
 * fix one element and use the Two Pointer technique
 * to find the remaining two elements.
 *
 * Since the array is sorted:
 *
 * • If the sum is too small,
 *   move the left pointer forward.
 *
 * • If the sum is too large,
 *   move the right pointer backward.
 *
 * • If the sum is zero,
 *   store the triplet and skip duplicates.
 *
 * ============================================================================
 */

import java.util.*;

class Solution {

    public List<List<Integer>> threeSum(int[] nums) {

        // Sort the array
        Arrays.sort(nums);

        List<List<Integer>> list = new ArrayList<>();

        // Fix the first element
        for (int i = 0; i < nums.length; i++) {

            // Skip duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int j = i + 1;
            int k = nums.length - 1;

            // Two Pointer Approach
            while (j < k) {

                int sum = nums[i] + nums[j] + nums[k];

                // Sum is smaller than 0
                if (sum < 0) {

                    j++;
                }

                // Sum is greater than 0
                else if (sum > 0) {

                    k--;
                }

                // Found a valid triplet
                else {

                    list.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    j++;
                    k--;

                    // Skip duplicate second elements
                    while (j < k && nums[j] == nums[j - 1])
                        j++;

                    // Skip duplicate third elements
                    while (j < k && nums[k] == nums[k + 1])
                        k--;
                }
            }
        }

        return list;
    }
}

/*
 * ============================================================================
 * Algorithm
 * ============================================================================
 *
 * Step 1:
 * Sort the array.
 *
 * Step 2:
 * Fix one element using index i.
 *
 * Step 3:
 * Place two pointers:
 *
 * j = i + 1
 * k = last index
 *
 * Step 4:
 *
 * Calculate:
 *
 * sum = nums[i] + nums[j] + nums[k]
 *
 * If sum < 0
 *      Move left pointer.
 *
 * If sum > 0
 *      Move right pointer.
 *
 * If sum == 0
 *
 *      Store the triplet.
 *
 *      Move both pointers.
 *
 *      Skip duplicate elements.
 *
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Input:
 *
 * [-1,0,1,2,-1,-4]
 *
 * After Sorting:
 *
 * [-4,-1,-1,0,1,2]
 *
 * -------------------------------------------------
 *
 * i = -4
 *
 * j = -1
 * k = 2
 *
 * Sum = -3
 *
 * Move j
 *
 * -------------------------------------------------
 *
 * i = -1
 *
 * j = -1
 * k = 2
 *
 * Sum = 0
 *
 * Store:
 *
 * [-1,-1,2]
 *
 * Move both pointers.
 *
 * -------------------------------------------------
 *
 * i = -1
 *
 * j = 0
 *
 * k = 1
 *
 * Sum = 0
 *
 * Store:
 *
 * [-1,0,1]
 *
 * Final Answer:
 *
 * [[-1,-1,2],[-1,0,1]]
 *
 * ============================================================================
 * Why Sorting?
 * ============================================================================
 *
 * Sorting helps us use the Two Pointer technique.
 *
 * Because the array is sorted:
 *
 * • Increasing the left pointer increases the sum.
 *
 * • Decreasing the right pointer decreases the sum.
 *
 * Without sorting,
 * Two Pointers cannot be used efficiently.
 *
 * ============================================================================
 * Why Skip Duplicates?
 * ============================================================================
 *
 * Example:
 *
 * nums =
 *
 * [-1,-1,-1,2,2]
 *
 * Without skipping duplicates,
 * the same triplet may be added multiple times.
 *
 * Therefore,
 *
 * Skip duplicate values for:
 *
 * ✔ First element (i)
 * ✔ Left pointer (j)
 * ✔ Right pointer (k)
 *
 * ============================================================================
 * Common Mistakes I Made
 * ============================================================================
 *
 * ❌ Forgot to sort the array.
 *
 * -------------------------------------------------
 *
 * ❌ Forgot to skip duplicate values.
 *
 * Result:
 * Duplicate triplets were added.
 *
 * -------------------------------------------------
 *
 * ❌ Forgot to move both pointers
 * after finding a valid triplet.
 *
 * This caused an infinite loop.
 *
 * -------------------------------------------------
 *
 * ❌ Used HashMap approach.
 *
 * Although it works,
 * handling duplicates becomes difficult.
 *
 * The Two Pointer approach is simpler
 * and is the expected interview solution.
 *
 * ============================================================================
 * Interview Follow-up
 * ============================================================================
 *
 * Q1. Why do we sort the array?
 *
 * So that we can use the Two Pointer technique
 * efficiently.
 *
 * -------------------------------------------------
 *
 * Q2. Why is the time complexity O(n²)?
 *
 * Sorting takes:
 *
 * O(n log n)
 *
 * The outer loop runs n times.
 *
 * The two pointers together traverse the array only once
 * for each fixed element.
 *
 * Total:
 *
 * O(n²)
 *
 * ============================================================================
 * Real-World Applications
 * ============================================================================
 *
 * 1. Financial Analysis
 * ---------------------
 * Finding three transactions whose total
 * equals a target balance.
 *
 * -------------------------------------------------
 *
 * 2. Data Analytics
 * -----------------
 * Finding combinations of values
 * that satisfy a required condition.
 *
 * -------------------------------------------------
 *
 * 3. Recommendation Systems
 * -------------------------
 * Selecting combinations of products
 * whose total price matches a customer's budget.
 *
 * ============================================================================
 * Pattern to Remember
 * ============================================================================
 *
 * Problems asking:
 *
 * "Find unique triplets"
 *
 * Think:
 *
 * Sort
 *      ↓
 * Fix One Element
 *      ↓
 * Two Pointers
 *      ↓
 * Skip Duplicates
 *
 * Similar Problems:
 *
 * ✔ Two Sum
 * ✔ 3Sum
 * ✔ 3Sum Closest
 * ✔ 4Sum
 * ✔ Container With Most Water
 *
 * ============================================================================
 */
