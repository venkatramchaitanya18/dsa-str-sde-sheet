/*
 * ============================================================================
 * Problem: Trapping Rain Water
 * Difficulty: Hard
 * LeetCode: 42
 * ============================================================================
 *
 * Problem Statement:
 * Given an array of non-negative integers representing the elevation map
 * where the width of each bar is 1, compute how much water it can trap
 * after raining.
 *
 * Example:
 * Input:
 * height = [0,1,0,2,1,0,1,3,2,1,2,1]
 *
 * Output:
 * 6
 *
 * ============================================================================
 * Better Approach: Prefix Maximum + Suffix Maximum Arrays
 * ============================================================================
 *
 * Idea:
 * - Water trapped at any index depends on:
 *
 *      Left Maximum Height
 *      Right Maximum Height
 *
 * - Water stored at index i:
 *
 *      min(leftMax, rightMax) - height[i]
 *
 * - Build:
 *      1. Prefix Maximum Array
 *      2. Suffix Maximum Array
 *
 * - Traverse once more to calculate total trapped water.
 *
 * Time Complexity : O(n)
 * Space Complexity: O(n)
 */

class Solution {

    public int trap(int[] height) {

        int n = height.length;

        int prefix[] = new int[n];
        int suffix[] = new int[n];

        // Build Prefix Maximum Array
        prefix[0] = height[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = Math.max(prefix[i - 1], height[i]);
        }

        // Build Suffix Maximum Array
        suffix[n - 1] = height[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1], height[i]);
        }

        // Calculate trapped water
        int total = 0;

        for (int i = 0; i < n; i++) {
            total += Math.min(prefix[i], suffix[i]) - height[i];
        }

        return total;
    }
}

/*
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * height = [4,2,0,3,2,5]
 *
 * Step 1: Prefix Maximum
 *
 * height :  4 2 0 3 2 5
 * prefix :  4 4 4 4 4 5
 *
 * ------------------------------------------------
 *
 * Step 2: Suffix Maximum
 *
 * height :  4 2 0 3 2 5
 * suffix :  5 5 5 5 5 5
 *
 * ------------------------------------------------
 *
 * Step 3:
 *
 * Index     Height  LeftMax RightMax Water
 * ----------------------------------------
 * 0           4        4       5      0
 * 1           2        4       5      2
 * 2           0        4       5      4
 * 3           3        4       5      1
 * 4           2        4       5      2
 * 5           5        5       5      0
 *
 * Total Water
 *
 * = 0 + 2 + 4 + 1 + 2 + 0
 * = 9
 *
 * ============================================================================
 * Why Formula Works
 * ============================================================================
 *
 * Water cannot rise above the shorter boundary.
 *
 * Therefore,
 *
 * Water Level =
 *
 *      min(Left Maximum, Right Maximum)
 *
 * Actual water stored:
 *
 *      Water Level - Current Height
 *
 * ============================================================================
 * Common Mistakes I Made
 * ============================================================================
 *
 * 1. Wrong suffix loop condition.
 *
 *    Wrote:
 *
 *      for(int i=n-2; i<=0; i--)
 *
 *    Correct:
 *
 *      for(int i=n-2; i>=0; i--)
 *
 *    Because we are moving backwards.
 *
 * ------------------------------------------------
 *
 * 2. Forgot to initialize:
 *
 *      prefix[0]
 *      suffix[n-1]
 *
 * ------------------------------------------------
 *
 * 3. Confused prefix and suffix traversal directions.
 *
 * ============================================================================
 * Pattern Learned
 * ============================================================================
 *
 * Prefix Maximum
 *
 * prefix[i] = max(prefix[i-1], height[i])
 *
 * ------------------------------------------------
 *
 * Suffix Maximum
 *
 * suffix[i] = max(suffix[i+1], height[i])
 *
 * ------------------------------------------------
 *
 * Water at each index
 *
 * min(prefix[i], suffix[i]) - height[i]
 *
 * ============================================================================
 * Similar Problems
 * ============================================================================
 *
 * ✓ Trapping Rain Water
 * ✓ Maximum Width Ramp
 * ✓ Buildings With an Ocean View
 * ✓ Largest Rectangle in Histogram (related concept)
 * ✓ Container With Most Water (two pointers)
 *
 * ============================================================================
 * Backend Java / Spring Boot Uses
 * ============================================================================
 *
 * Although this exact problem is interview-focused,
 * the Prefix/Suffix technique is used in:
 *
 * • Preprocessing large datasets
 * • Analytics dashboards
 * • Financial cumulative calculations
 * • Prefix/Suffix aggregation queries
 * • Dynamic programming optimizations
 *
 * ============================================================================
 * Optimal Solution
 * ============================================================================
 *
 * This Prefix + Suffix approach is a Better Solution.
 *
 * Time  : O(n)
 * Space : O(n)
 *
 * There is a more optimal solution:
 *
 * ✓ Two Pointer Technique
 *
 * Time  : O(n)
 * Space : O(1)
 *
 * It removes the need for prefix and suffix arrays.
 * ============================================================================
 */
