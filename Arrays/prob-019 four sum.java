/*
 * ============================================================================
 *                                 4SUM
 * ============================================================================
 * Difficulty : Medium
 *
 * Time Complexity  : O(n³)
 * Space Complexity : O(1) (Ignoring the output list)
 *
 * ============================================================================
 * Problem Statement
 * ============================================================================
 *
 * Given an integer array nums and an integer target,
 * return all unique quadruplets
 *
 *      [nums[a], nums[b], nums[c], nums[d]]
 *
 * such that:
 *
 *      a != b != c != d
 *
 * and
 *
 *      nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * ============================================================================
 * Intuition
 * ============================================================================
 *
 * A brute-force solution checks every possible combination
 * of four elements, resulting in O(n⁴).
 *
 * Instead,
 * we sort the array first.
 *
 * Then,
 * fix the first two elements using two loops,
 * and use the Two Pointer approach
 * to find the remaining two elements.
 *
 * This reduces the complexity to O(n³).
 *
 * ============================================================================
 */

import java.util.*;

class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;

        // Sort the array
        Arrays.sort(nums);

        // Fix the first element
        for (int i = 0; i < n - 3; i++) {

            // Skip duplicate first elements
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // Fix the second element
            for (int j = i + 1; j < n - 2; j++) {

                // Skip duplicate second elements
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                int k = j + 1;
                int l = n - 1;

                // Two Pointer Approach
                while (k < l) {

                    // Use long to avoid integer overflow
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];

                    // Sum is smaller than target
                    if (sum < target) {

                        k++;
                    }

                    // Sum is greater than target
                    else if (sum > target) {

                        l--;
                    }

                    // Quadruplet found
                    else {

                        ans.add(Arrays.asList(
                                nums[i],
                                nums[j],
                                nums[k],
                                nums[l]
                        ));

                        k++;
                        l--;

                        // Skip duplicate third elements
                        while (k < l && nums[k] == nums[k - 1])
                            k++;

                        // Skip duplicate fourth elements
                        while (k < l && nums[l] == nums[l + 1])
                            l--;
                    }
                }
            }
        }

        return ans;
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
 * Fix the first element (i).
 *
 * Step 3:
 * Fix the second element (j).
 *
 * Step 4:
 * Use Two Pointers.
 *
 * k = j + 1
 * l = n - 1
 *
 * Step 5:
 *
 * Calculate:
 *
 * sum = nums[i] + nums[j] + nums[k] + nums[l]
 *
 * If sum < target
 *      k++
 *
 * If sum > target
 *      l--
 *
 * If sum == target
 *
 *      Store the quadruplet.
 *
 *      Move both pointers.
 *
 *      Skip duplicate values.
 *
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Input:
 *
 * nums = [1,0,-1,0,-2,2]
 *
 * target = 0
 *
 * After Sorting:
 *
 * [-2,-1,0,0,1,2]
 *
 * ----------------------------------------------------
 *
 * i = -2
 * j = -1
 *
 * k = 0
 * l = 2
 *
 * Sum = -1
 *
 * Move k
 *
 * ----------------------------------------------------
 *
 * k = 0
 * l = 2
 *
 * Sum = 0
 *
 * Store:
 *
 * [-2,-1,1,2]
 *
 * ----------------------------------------------------
 *
 * Continue searching...
 *
 * Final Answer:
 *
 * [
 *   [-2,-1,1,2],
 *   [-2,0,0,2],
 *   [-1,0,0,1]
 * ]
 *
 * ============================================================================
 * Why Sorting?
 * ============================================================================
 *
 * Sorting allows us to use the Two Pointer technique.
 *
 * Since the array is sorted:
 *
 * • Increasing k increases the sum.
 *
 * • Decreasing l decreases the sum.
 *
 * Without sorting,
 * Two Pointers cannot be applied efficiently.
 *
 * ============================================================================
 * Why Skip Duplicates?
 * ============================================================================
 *
 * Example:
 *
 * nums =
 *
 * [-2,-2,0,0,2,2]
 *
 * Without skipping duplicates,
 * the same quadruplet
 * would be added multiple times.
 *
 * Therefore,
 * skip duplicate values for:
 *
 * ✔ First element (i)
 *
 * ✔ Second element (j)
 *
 * ✔ Third pointer (k)
 *
 * ✔ Fourth pointer (l)
 *
 * ============================================================================
 * Why use long?
 * ============================================================================
 *
 * Example:
 *
 * nums =
 *
 * [1000000000,
 * 1000000000,
 * 1000000000,
 * 1000000000]
 *
 * Sum exceeds Integer.MAX_VALUE.
 *
 * Using:
 *
 * long sum
 *
 * prevents integer overflow.
 *
 * ============================================================================
 * Common Mistakes I Made
 * ============================================================================
 *
 * ❌ Forgot to sort the array.
 *
 * ----------------------------------------------------
 *
 * ❌ Forgot to skip duplicate values.
 *
 * Result:
 * Duplicate quadruplets.
 *
 * ----------------------------------------------------
 *
 * ❌ Used int instead of long.
 *
 * Large test cases failed because of overflow.
 *
 * Correct:
 *
 * long sum =
 * (long)nums[i] + nums[j] + nums[k] + nums[l];
 *
 * ----------------------------------------------------
 *
 * ❌ Forgot to move both pointers
 * after finding a valid quadruplet.
 *
 * This caused an infinite loop.
 *
 * ----------------------------------------------------
 *
 * ❌ Forgot duplicate checks for
 * i, j, k and l.
 *
 * ============================================================================
 * Interview Follow-up
 * ============================================================================
 *
 * Q1. Why is the complexity O(n³)?
 *
 * Sorting:
 *
 * O(n log n)
 *
 * Outer Loop:
 * O(n)
 *
 * Second Loop:
 * O(n)
 *
 * Two Pointers:
 * O(n)
 *
 * Total:
 *
 * O(n³)
 *
 * ----------------------------------------------------
 *
 * Q2. Can we solve it faster?
 *
 * No.
 *
 * O(n³) is considered the optimal solution
 * for the general 4Sum problem.
 *
 * ============================================================================
 * Real-World Applications
 * ============================================================================
 *
 * 1. Financial Analytics
 * ----------------------
 * Finding four transactions whose total
 * equals a required amount.
 *
 * ----------------------------------------------------
 *
 * 2. Recommendation Systems
 * -------------------------
 * Finding combinations of four products
 * matching a customer's budget.
 *
 * ----------------------------------------------------
 *
 * 3. Data Mining
 * --------------
 * Searching for groups of values
 * satisfying a target condition.
 *
 * ============================================================================
 * Pattern to Remember
 * ============================================================================
 *
 * K-Sum Pattern
 *
 * 2Sum
 *      → HashMap / Two Pointers
 *
 * 3Sum
 *      → Sort + Fix One + Two Pointers
 *
 * 4Sum
 *      → Sort + Fix Two + Two Pointers
 *
 * KSum
 *      → Recursion + Two Pointers
 *
 * ============================================================================
 */
