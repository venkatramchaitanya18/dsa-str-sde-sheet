/*
 * ============================================================================
 * Problem Name  : Maximum Subarray (Kadane's Algorithm)
 *
 * Platform      : LeetCode 53
 *
 * Difficulty    : Medium
 *
 * Topic         : Arrays, Dynamic Programming
 *
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 * ============================================================================
 */

class Solution {

    public int maxSubArray(int[] nums) {

        // Stores the running sum of the current subarray
        int sum = 0;

        // Stores the maximum subarray sum found so far
        int max = Integer.MIN_VALUE;

        // Traverse each element in the array
        for (int i = 0; i < nums.length; i++) {

            // Add the current element to the running sum
            sum += nums[i];

            // Update the maximum sum if the current running sum is larger
            if (sum > max) {
                max = sum;
            }

            // If the running sum becomes negative,
            // it cannot contribute to a larger subarray.
            // Start a new subarray from the next element.
            if (sum < 0) {
                sum = 0;
            }
        }

        // Return the maximum subarray sum
        return max;
    }
}

/*
===============================================================================
Problem Statement
===============================================================================

Given an integer array nums, find the contiguous subarray
(containing at least one element) that has the largest sum
and return that sum.

Example:

Input:
[-2,1,-3,4,-1,2,1,-5,4]

Output:
6

Explanation:
The subarray [4,-1,2,1] has the maximum sum.

===============================================================================
Intuition
===============================================================================

A brute-force solution checks every possible subarray,
resulting in O(n²) time complexity.

Kadane's Algorithm improves this to O(n) by maintaining a
running sum while traversing the array.

Key Observation:
If the running sum becomes negative, it can never increase
the sum of any future subarray.
So, discard it and start a new subarray.

===============================================================================
Algorithm
===============================================================================

Step 1:
Initialize:

sum = 0
max = Integer.MIN_VALUE

------------------------------------------------------------

Step 2:
Traverse the array from left to right.

Add the current element to the running sum.

sum += nums[i]

------------------------------------------------------------

Step 3:
Update the maximum sum whenever the current running sum is larger.

------------------------------------------------------------

Step 4:
If the running sum becomes negative,
reset it to 0 because it will only reduce the sum
of future subarrays.

===============================================================================
Dry Run
===============================================================================

Input:

[-2,1,-3,4,-1,2,1,-5,4]

Element    Running Sum    Maximum Sum
--------------------------------------
-2         -2             -2
reset       0

1           1              1

-3         -2              1
reset       0

4           4              4

-1          3              4

2           5              5

1           6              6

-5          1              6

4           5              6

Final Answer = 6

===============================================================================
Interview Follow-up
===============================================================================

Q1. Why do we reset the running sum when it becomes negative?

Answer:
A negative running sum can never increase the sum of a future
subarray. Starting from the next element always gives a better result.

------------------------------------------------------------

Q2. Can we also print the maximum sum subarray?

Yes.
Maintain the starting index of the current subarray and update
the start and end indices whenever a new maximum sum is found.

===============================================================================
Real-World Applications
===============================================================================

1. Stock Market Analysis

Used to identify the most profitable continuous period for
buying and selling based on daily profit/loss values.

------------------------------------------------------------

2. Monitoring User Activity or Server Performance

Companies analyze continuous metrics such as website traffic,
CPU usage, sales, or user engagement to find the time interval
with the highest overall activity.

===============================================================================
Key Takeaways
===============================================================================

✔ Kadane's Algorithm solves the problem in O(n) time.

✔ A negative running sum is never useful for future subarrays.

✔ Always remember:
   "Negative Prefix -> Remove it."

✔ Space Complexity is O(1) because no extra array is used.

===============================================================================
*/
