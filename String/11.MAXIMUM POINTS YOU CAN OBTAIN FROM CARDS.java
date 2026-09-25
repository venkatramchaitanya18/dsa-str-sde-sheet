/*
================================================================================
              1423. MAXIMUM POINTS YOU CAN OBTAIN FROM CARDS
================================================================================

LeetCode: 1423
Difficulty: Medium
Pattern: Sliding Window / Array

Problem:
There are cards arranged in a row. Each card has some points.
In one step, you can take one card from the beginning or the end of
 the row. You must take exactly k cards.

Return the maximum score you can obtain.

Example:
    cardPoints = [1,2,3,4,5,6,1]
    k = 3
    Answer = 12

================================================================================
                              BRUTE FORCE
================================================================================

IDEA:
There are k + 1 possible ways to choose exactly k cards:
    0 left + k right
    1 left + k-1 right
    ...
    k left + 0 right

For every possibility, calculate the sum again.

Time: O(k^2)
Space: O(1)
*/

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int max = Integer.MIN_VALUE;
        int left = -1;
        int right = k;

        for(int i = 0; i < k + 1; i++) {
            int sum = 0;

            for(int l = 0; l <= left; l++) {
                sum += cardPoints[l];
            }

            for(int j = cardPoints.length - 1;
                j >= cardPoints.length - right; j--) {
                sum += cardPoints[j];
            }

            max = Math.max(sum, max);
            left++;
            right--;
        }
        return max;
    }
}

/*
================================================================================
                         OPTIMIZED APPROACH
================================================================================

Start with k cards from the right.

Then move one card at a time from the right selection to the left selection.

Instead of recalculating the entire sum:

    new sum = old sum + left card - right card

This checks all k + 1 valid combinations in O(k).

================================================================================
                         OPTIMIZED CODE
================================================================================
*/

class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int sum = 0;

        // First take k cards from the right.
        for(int j = cardPoints.length - 1;
            j >= cardPoints.length - k;
            j--) {
            sum += cardPoints[j];
        }

        int max = sum;
        int right = cardPoints.length - k;

        for(int i = 0; i < k; i++) {
            // Add one card from the left and remove one from the right.
            sum += cardPoints[i] - cardPoints[right];
            right++;
            max = Math.max(sum, max);
        }

        return max;
    }
}

/*
================================================================================
                         OPTIMIZED DRY RUN
================================================================================

cardPoints = [1,2,3,4,5,6,1]
k = 3

Initial: take 3 from right
    5 + 6 + 1 = 12
    sum = 12, max = 12

Next:
    add 1 from left, remove 5 from right
    12 + 1 - 5 = 8

Next:
    add 2 from left, remove 6 from right
    8 + 2 - 6 = 4

Next:
    add 3 from left, remove 1 from right
    4 + 3 - 1 = 6

Checked scores:
    12, 8, 4, 6

Answer = 12

================================================================================
                              COMPLEXITY
================================================================================

Brute Force:
    Time  : O(k^2)
    Space : O(1)

Optimized:
    Time  : O(k)
    Space : O(1)

================================================================================
                         IMPORTANT PATTERN
================================================================================

When the current selection changes by one element:

    newSum = oldSum + enteringElement - leavingElement

Do not recalculate the complete sum.

================================================================================
                         COMMON MISTAKES
================================================================================

1. Starting max with 0 can fail for all-negative arrays.
2. Resetting left/right inside the outer loop prevents them from progressing.
3. Exactly k cards must be selected.
4. You cannot choose arbitrary k cards from the middle.

================================================================================
                         INTERVIEW TAKEAWAY
================================================================================

There are only k + 1 valid left/right combinations.
Start with k cards from the right, then gradually replace right cards
with left cards. This turns repeated recalculation into O(k).

================================================================================
*/
