/*
 * ============================================================================
 *                         74. Search a 2D Matrix
 * ============================================================================
 *
 * Difficulty : Medium
 *
 * Topic      : Binary Search
 *
 * ----------------------------------------------------------------------------
 * Problem Statement
 * ----------------------------------------------------------------------------
 *
 * You are given an m x n matrix with the following properties:
 *
 * 1. Every row is sorted in non-decreasing order.
 *
 * 2. The first element of each row is greater than
 *    the last element of the previous row.
 *
 * Return true if the target exists in the matrix,
 * otherwise return false.
 *
 * You must solve it in O(log(m × n)) time.
 *
 * ============================================================================
 * Approach 1 : Find the Correct Row + Binary Search
 * ============================================================================
 *
 * Intuition
 * ----------
 *
 * Since every row is sorted,
 * first find the row where the target can possibly exist.
 *
 * After finding the row,
 * perform Binary Search on that row.
 *
 * Time Complexity : O(m + log n)
 *
 * Space Complexity : O(1)
 *
 * NOTE:
 * This approach is NOT accepted for the expected complexity
 * because LeetCode requires O(log(m × n)).
 *
 * ============================================================================
 */

class Solution {

    // Binary Search on a particular row
    public boolean binarySearch(int[][] matrix, int row, int target) {

        int low = 0;
        int high = matrix[0].length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (matrix[row][mid] == target) {
                return true;
            }

            else if (matrix[row][mid] < target) {
                low = mid + 1;
            }

            else {
                high = mid - 1;
            }
        }

        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {

        int cols = matrix[0].length;

        // Find the row where target can exist
        for (int i = 0; i < matrix.length; i++) {

            if (target >= matrix[i][0] &&
                target <= matrix[i][cols - 1]) {

                return binarySearch(matrix, i, target);
            }
        }

        return false;
    }
}

/*
 * ============================================================================
 * Dry Run (Approach 1)
 * ============================================================================
 *
 * Matrix
 *
 * 1   3   5   7
 * 10 11 16 20
 * 23 30 34 60
 *
 * Target = 16
 *
 * Row 0
 *
 * 16 >= 1  ✓
 * 16 <= 7  ✗
 *
 * Skip Row 0
 *
 * Row 1
 *
 * 16 >= 10 ✓
 * 16 <= 20 ✓
 *
 * Binary Search on
 *
 * 10 11 16 20
 *
 * Target Found.
 *
 * ============================================================================
 * Approach 2 : Binary Search on Entire Matrix (Optimal)
 * ============================================================================
 *
 * Intuition
 * ----------
 *
 * Observe the matrix carefully.
 *
 * Property 1
 * Every row is sorted.
 *
 * Property 2
 * First element of every row >
 * Last element of previous row.
 *
 * Therefore,
 * the entire matrix behaves like one sorted array.
 *
 * Example
 *
 * Matrix
 *
 * 1   3   5   7
 * 10 11 16 20
 * 23 30 34 60
 *
 * Imagine removing row boundaries.
 *
 * 1 3 5 7 10 11 16 20 23 30 34 60
 *
 * Now simply perform Binary Search.
 *
 * ============================================================================
 * Mapping Virtual Index to Matrix
 * ============================================================================
 *
 * Suppose
 *
 * Rows = 3
 * Cols = 4
 *
 * Virtual Index
 *
 * 0 1 2 3
 * 4 5 6 7
 * 8 9 10 11
 *
 * Formula
 *
 * row = mid / cols
 *
 * col = mid % cols
 *
 * Example
 *
 * mid = 9
 *
 * row = 9 / 4 = 2
 *
 * col = 9 % 4 = 1
 *
 * matrix[2][1] = 30
 *
 * ============================================================================
 */

class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Treat the matrix as one virtual sorted array.
        int low = 0;
        int high = rows * cols - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            // Convert virtual index into row and column.
            int row = mid / cols;
            int col = mid % cols;

            if (matrix[row][col] == target) {
                return true;
            }

            else if (matrix[row][col] < target) {
                low = mid + 1;
            }

            else {
                high = mid - 1;
            }
        }

        return false;
    }
}

/*
 * ============================================================================
 * Dry Run (Optimal Approach)
 * ============================================================================
 *
 * Matrix
 *
 * 1   3   5   7
 * 10 11 16 20
 * 23 30 34 60
 *
 * Target = 16
 *
 * rows = 3
 * cols = 4
 *
 * Total Elements = 12
 *
 * low = 0
 *
 * high = 11
 *
 * --------------------------------------------------
 *
 * mid = 5
 *
 * row = 5 / 4 = 1
 *
 * col = 5 % 4 = 1
 *
 * matrix[1][1] = 11
 *
 * 11 < 16
 *
 * Move Right
 *
 * low = 6
 *
 * --------------------------------------------------
 *
 * mid = 8
 *
 * row = 8 / 4 = 2
 *
 * col = 8 % 4 = 0
 *
 * matrix[2][0] = 23
 *
 * 23 > 16
 *
 * Move Left
 *
 * high = 7
 *
 * --------------------------------------------------
 *
 * mid = 6
 *
 * row = 6 / 4 = 1
 *
 * col = 6 % 4 = 2
 *
 * matrix[1][2] = 16
 *
 * Found
 *
 * ============================================================================
 * Time Complexity
 * ============================================================================
 *
 * Approach 1
 *
 * Finding Row      : O(m)
 *
 * Binary Search    : O(log n)
 *
 * Overall          : O(m + log n)
 *
 * --------------------------------------------------
 *
 * Approach 2
 *
 * Binary Search on m × n elements
 *
 * O(log(m × n))
 *
 * Space Complexity
 *
 * O(1)
 *
 * ============================================================================
 * Interview Follow-up
 * ============================================================================
 *
 * Q1. Why is Approach 1 not accepted?
 *
 * Because scanning rows takes O(m),
 * while the question explicitly asks for O(log(m × n)).
 *
 * ---------------------------------------------------------------------------
 *
 * Q2. Why does row = mid / cols work?
 *
 * Every 'cols' elements belong to one row.
 * Integer division tells us the row number.
 *
 * ---------------------------------------------------------------------------
 *
 * Q3. Why does col = mid % cols work?
 *
 * Modulus gives the remaining position
 * inside the current row.
 *
 * ---------------------------------------------------------------------------
 *
 * Q4. What is the key observation?
 *
 * Since
 *
 * First element of next row >
 * Last element of previous row
 *
 * the whole matrix is globally sorted.
 *
 * ============================================================================
 * Pattern Recognition
 * ============================================================================
 *
 * Whenever you see:
 *
 * ✔ Every row is sorted.
 *
 * ✔ First element of next row >
 *   Last element of previous row.
 *
 * Immediately think:
 *
 *              2D Matrix
 *                  │
 *                  ▼
 *      Virtual Sorted 1D Array
 *                  │
 *                  ▼
 *          Binary Search
 *                  │
 *                  ▼
 *       row = mid / cols
 *       col = mid % cols
 *
 * ============================================================================
 * Real-World Applications
 * ============================================================================
 *
 * 1. Database Index Search
 *
 * Databases store records in sorted pages.
 * Instead of checking each page one by one,
 * Binary Search quickly finds the required record.
 *
 * ---------------------------------------------------------------------------
 *
 * 2. Large Log & Analytics Search
 *
 * Large sorted log files or analytical datasets
 * can be viewed as one logical sorted sequence.
 *
 * Binary Search allows fast searching
 * without scanning the complete dataset.
 *
 * ============================================================================
 * Similar Pattern Problems
 * ============================================================================
 *
 * ✔ Binary Search
 *
 * ✔ Search Insert Position
 *
 * ✔ First Bad Version
 *
 * ✔ Peak Element
 *
 * ✔ Find Minimum in Rotated Sorted Array
 *
 * ✔ Search in Rotated Sorted Array
 *
 * ✔ Kth Smallest Element in a Sorted Matrix
 *
 * ✔ Median of Two Sorted Arrays
 *
 * ============================================================================
 */
