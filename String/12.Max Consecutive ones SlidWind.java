/*
================================================================================
LeetCode 1004 - Max Consecutive Ones III
Difficulty: Medium
Pattern: Sliding Window / Two Pointers

Problem:
Given a binary array nums and an integer k, return the maximum number of
consecutive 1's in the array if you can flip at most k 0's.

Example:
nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1]
k = 3
Output = 10
================================================================================
CODE
================================================================================
*/

class Solution {
    public int longestOnes(int[] nums, int k) {
        int start = 0;
        int zeros = 0;
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeros++;
            }

            while (zeros > k) {
                if (nums[start] == 0) {
                    zeros--;
                }
                start++;
            }

            max = Math.max(max, i - start + 1);
        }
        return max;
    }
}

/*
================================================================================
1. INTUITION
================================================================================

We need the longest consecutive sequence of 1's.

We can flip at most k zeros into 1's.
Instead of actually flipping them, find the longest subarray containing
at most k zeros.

For k = 3:
0, 1, 2, or 3 zeros -> valid
4 zeros -> invalid

This is a variable-size Sliding Window problem.

================================================================================
2. VARIABLES
================================================================================

start -> left boundary of the window

i     -> right boundary of the window

zeros -> number of zeros currently inside the window

max   -> longest valid window found so far

================================================================================
3. LOGIC
================================================================================

1. Expand the window using i.
2. If nums[i] == 0, increment zeros.
3. If zeros > k, shrink from the left.
4. When nums[start] == 0 leaves the window, decrement zeros.
5. Once zeros <= k, the window is valid.
6. Window length = i - start + 1.
7. Update max.

================================================================================
4. EXAMPLE 2 DRY RUN
================================================================================

nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1]
k = 3

A longest valid window is indices 2 through 11:

index:  2  3  4  5  6  7  8  9  10 11
value:  1  1  0  0  1  1  1  0   1  1

It contains exactly 3 zeros, so all three can be flipped:

[1,1,0,0,1,1,1,0,1,1]
          | |       |
          flip      flip

After flipping:

[1,1,1,1,1,1,1,1,1,1]

Length = 10, so answer = 10.

================================================================================
5. WHY SHRINK?
================================================================================

If k = 3 and the window contains 4 zeros, it is invalid.

Move start forward until one zero leaves the window.
Then zeros becomes 3 and the window is valid again.

================================================================================
6. WHY NOT ACTUALLY FLIP?
================================================================================

If a window contains at most k zeros, we already know those zeros can be
flipped. Therefore we only need to count zeros; modifying the array is
unnecessary.

================================================================================
7. KEY SLIDING WINDOW PATTERN
================================================================================

Expand -> count bad elements -> shrink if needed -> update answer

Here:
    bad element = 0
    maximum allowed bad elements = k

Think:
    "Find the longest subarray with at most k zeros."

================================================================================
8. WHY i - start + 1?
================================================================================

If start = 2 and i = 7:

indices = 2,3,4,5,6,7

number of elements = 7 - 2 + 1 = 6

Therefore:
    window length = i - start + 1

================================================================================
9. COMMON MISTAKES
================================================================================

Mistake 1:
Changing k directly with k-- and k++ can make its meaning confusing.

Better:
    k     = maximum allowed zeros
    zeros = current zeros in the window

Mistake 2:
Using start[i].

start is an integer index, so use nums[start].

================================================================================
10. COMPLEXITY
================================================================================

Time: O(n)
Space: O(1)

Both pointers only move from left to right.

================================================================================
11. INTERVIEW EXPLANATION
================================================================================

"I use a variable-size sliding window. I maintain a window containing at
most k zeros because those zeros can be flipped to ones. I expand the
window with the right pointer. If the number of zeros becomes greater
than k, I move the left pointer forward until the window is valid again.
For every valid window, I calculate its length and keep the maximum."

================================================================================
12. RELATED SLIDING WINDOW PROBLEMS
================================================================================

- Fruit Into Baskets
- Longest Substring Without Repeating Characters
- Longest Repeating Character Replacement
- Subarrays with K Different Integers
- Minimum Size Subarray Sum

================================================================================
13. BACKEND / REAL-WORLD CONNECTION
================================================================================

The same pattern can be used for continuous data such as logs or request
streams where we need the longest period containing at most K invalid,
failed, or error events.

================================================================================
14. INTERVIEW TAKEAWAY
================================================================================

Do not think:
    "How do I flip the zeros?"

Think:
    "What is the longest window containing at most k zeros?"

That observation converts the problem into a Sliding Window problem.
================================================================================
*/
