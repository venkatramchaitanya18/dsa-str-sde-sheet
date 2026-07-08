/*
 * ============================================================================
 *                             UNIQUE PATHS
 * ============================================================================
 * Difficulty : Medium
 *
 * Time Complexity
 * ----------------
 * 1. Recursion      : O(2^(m+n))
 * 2. Memoization    : O(m × n)
 * 3. Tabulation     : O(m × n)
 *
 * Space Complexity
 * -----------------
 * 1. Recursion      : O(m+n)
 * 2. Memoization    : O(m×n) + O(m+n)
 * 3. Tabulation     : O(m×n)
 * ============================================================================
 */

import java.util.Arrays;

public class UniquePaths {

    //=========================================================================
    // APPROACH 1 : RECURSION
    //=========================================================================

    /*
     * Intuition:
     * ----------
     * To reach the current cell,
     * we can either come from:
     *
     * 1. Top
     * 2. Left
     *
     * Therefore,
     *
     * uniquePaths(m,n)
     * =
     * uniquePaths(m-1,n)
     * +
     * uniquePaths(m,n-1)
     */

    public static int uniquePathsRecursion(int m, int n) {

        // Outside the grid
        if (m == 0 || n == 0)
            return 0;

        // Reached destination (1×1 grid)
        if (m == 1 && n == 1)
            return 1;

        // Total paths = Top + Left
        return uniquePathsRecursion(m - 1, n)
                + uniquePathsRecursion(m, n - 1);
    }

    //=========================================================================
    // APPROACH 2 : MEMOIZATION (TOP-DOWN DP)
    //=========================================================================

    /*
     * Intuition:
     * ----------
     * Store already calculated answers.
     *
     * Before calculating a state,
     * check whether it already exists in DP.
     */

    public static int uniquePathsMemoization(int m, int n) {

        int dp[][] = new int[m + 1][n + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solve(m, n, dp);
    }

    private static int solve(int m, int n, int[][] dp) {

        // Outside grid
        if (m == 0 || n == 0)
            return 0;

        // Destination
        if (m == 1 && n == 1)
            return 1;

        // Already calculated
        if (dp[m][n] != -1)
            return dp[m][n];

        // Store answer
        dp[m][n] = solve(m - 1, n, dp)
                + solve(m, n - 1, dp);

        return dp[m][n];
    }

    //=========================================================================
    // APPROACH 3 : TABULATION (BOTTOM-UP DP)
    //=========================================================================

    /*
     * Intuition:
     * ----------
     * Instead of recursion,
     * fill the DP table from smaller states to bigger states.
     *
     * State:
     *
     * dp[i][j]
     *
     * =
     *
     * dp[i-1][j]
     * +
     * dp[i][j-1]
     */

    public static int uniquePathsTabulation(int m, int n) {

        int dp[][] = new int[m][n];

        // Starting point
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Skip starting cell
                if (i == 0 && j == 0)
                    continue;

                int top = 0;
                int left = 0;

                if (i > 0)
                    top = dp[i - 1][j];

                if (j > 0)
                    left = dp[i][j - 1];

                // Current = Top + Left
                dp[i][j] = top + left;
            }
        }

        return dp[m - 1][n - 1];
    }

    //=========================================================================
    // MAIN METHOD
    //=========================================================================

    public static void main(String[] args) {

        int m = 3;
        int n = 7;

        System.out.println("Recursion    : " + uniquePathsRecursion(m, n));
        System.out.println("Memoization  : " + uniquePathsMemoization(m, n));
        System.out.println("Tabulation   : " + uniquePathsTabulation(m, n));
    }
}

/*
===============================================================================
Detailed Explanation
===============================================================================

Recursion
---------

Think from the destination.

To reach (m,n),
we must come either from

1. (m-1,n)
2. (m,n-1)

Recurrence:

uniquePaths(m,n)
=
uniquePaths(m-1,n)
+
uniquePaths(m,n-1)

Problem:

Same states are calculated multiple times.

===============================================================================

Memoization
-----------

Store already calculated answers inside a DP array.

Before solving any state:

if(dp[m][n]!=-1)
    return dp[m][n];

This avoids repeated calculations.

===============================================================================

Tabulation
-----------

Instead of recursion,

build the answer from smaller states.

State:

dp[i][j]

Meaning:

Number of ways to reach cell (i,j).

Transition:

dp[i][j]
=
dp[i-1][j]
+
dp[i][j-1]

===============================================================================

Dry Run (3 × 3)

Initial DP

0 0 0
0 0 0
0 0 0

↓

dp[0][0]=1

1 0 0
0 0 0
0 0 0

↓

1 1 1
1 2 3
1 3 6

Answer = 6

===============================================================================

Interview Follow-up

1. Can we optimize space?

Yes.

Instead of a 2D DP array,

use one 1D array.

Space = O(n)

--------------------------------------------------

2. Can we solve without DP?

Yes.

Combination Formula

C(m+n-2,m-1)

===============================================================================

Real-World Applications

1. Robot Navigation
   Finding the number of possible routes.

2. Game Development
   Counting paths on grid maps.

3. Network Routing
   Counting possible routes between nodes.

4. Dynamic Programming
   Basic DP problem for learning
   Recursion → Memoization → Tabulation.

===============================================================================

Common Mistakes I Made

❌ Created DP inside recursion.

❌ Used dp[m][n] with new int[m][n].

❌ Forgot to initialize DP with -1.

❌ Overwrote dp[0][0].

❌ Swapped m and n in loops.

Correct:

for(i<m)
    for(j<n)

===============================================================================
*/
