/*
 * ============================================================================
 * Problem Name  : Best Time to Buy and Sell Stock
 *
 * Platform      : LeetCode 121
 *
 * Difficulty    : Easy
 *
 * Topic         : Arrays, Greedy
 *
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 * ============================================================================
 */

class Solution {

    public int maxProfit(int[] prices) {

        // Assume we buy the stock on the first day.
        // 'buy' stores the minimum buying price seen so far.
        int buy = prices[0];

        // Stores the maximum profit found so far.
        int maxProfit = Integer.MIN_VALUE;

        // Traverse the array from the second day.
        for (int i = 1; i < prices.length; i++) {

            // Calculate the profit if we sell on the current day.
            int profit = prices[i] - buy;

            // Update the maximum profit if the current profit is larger.
            maxProfit = Math.max(maxProfit, profit);

            // If today's price is smaller,
            // update the buying price.
            if (buy > prices[i]) {
                buy = prices[i];
            }
        }

        // If every profit is negative,
        // no transaction should be made.
        if (maxProfit < 0)
            return 0;

        return maxProfit;
    }
}

/*
===============================================================================
Problem Statement
===============================================================================

You are given an array where prices[i] represents the price of a stock
on the ith day.

Choose one day to buy the stock and a different future day to sell it.

Return the maximum profit possible.

If no profit can be made, return 0.

Example:

Input:
[7,1,5,3,6,4]

Output:
5

Explanation:

Buy at price = 1

Sell at price = 6

Profit = 6 - 1 = 5

===============================================================================
Intuition
===============================================================================

To maximize profit,

we should buy at the lowest price
and sell at the highest price after buying.

Instead of checking every pair of days (O(n²)),
we keep track of the minimum buying price seen so far.

For every day,

Current Profit = Today's Price - Minimum Buying Price

Update the maximum profit whenever we find a larger profit.

===============================================================================
Algorithm
===============================================================================

Step 1:

Assume the first day's price is the buying price.

buy = prices[0]

------------------------------------------------------------

Step 2:

Traverse the remaining days.

For each day,

calculate

profit = prices[i] - buy

------------------------------------------------------------

Step 3:

Update the maximum profit.

maxProfit = max(maxProfit, profit)

------------------------------------------------------------

Step 4:

If today's stock price is smaller than the current buying price,

update the buying price.

buy = prices[i]

===============================================================================
Dry Run
===============================================================================

Input:

[7,1,5,3,6,4]

------------------------------------------------------------

Day    Price    Buy Price    Profit    Max Profit

1       7          7            -          -

2       1          1           -6          -6

3       5          1            4           4

4       3          1            2           4

5       6          1            5           5

6       4          1            3           5

Final Answer = 5

===============================================================================
Interview Follow-up
===============================================================================

Q1. Why do we update the buying price whenever a smaller value is found?

Answer:

Buying at a lower price always gives the possibility
of making a larger profit in the future.

------------------------------------------------------------

Q2. Why don't we sell before buying?

Because while traversing the array,

the buying price is always chosen from the previous days.

Therefore,

Buy Day < Sell Day

is always maintained.

------------------------------------------------------------

Q3. Can we solve it using Dynamic Programming?

Yes.

However, the Greedy solution is simpler,
uses constant space,
and is optimal.

===============================================================================
Real-World Applications
===============================================================================

1. Stock Market Analysis

Investment applications use this idea to determine
the maximum possible profit from historical stock prices.

------------------------------------------------------------

2. E-Commerce Price Tracking

Shopping and price comparison platforms analyze
historical product prices to identify the best time
to purchase an item at the lowest cost.

===============================================================================
Pattern
===============================================================================

Pattern Name:

Greedy + One Pass

Other Problems Using This Pattern:

• Maximum Difference Between Increasing Elements
• Best Time to Buy and Sell Stock II
• Gas Station
• Jump Game
• Candy Distribution

===============================================================================
Key Takeaways
===============================================================================

✔ Traverse the array only once.

✔ Always maintain the minimum buying price.

✔ Calculate profit for every day.

✔ Update the maximum profit whenever a better profit is found.

✔ Greedy works because buying at the minimum price
  always maximizes the future profit.

✔ Time Complexity : O(n)

✔ Space Complexity : O(1)

===============================================================================
*/
