/*
 * ============================================================================
 * Problem Name  : Rotate Image
 *
 * Platform      : LeetCode 48
 *
 * Difficulty    : Medium
 *
 * Topic         : Arrays, Matrix
 *
 * Time Complexity  : O(n²)
 * Space Complexity : O(1)
 * ============================================================================
 */

class Solution {

    public void rotate(int[][] matrix) {

        int n = matrix.length;

        // Step 1: Transpose the matrix.
        // Convert rows into columns by swapping matrix[i][j] with matrix[j][i].
        // Start j from (i + 1) to avoid swapping the same elements twice.
        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse every row.
        // Reverse only half of the row because each swap places
        // two elements in their correct positions.
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n / 2; j++) {

                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }
}

/*
===============================================================================
Problem Statement
===============================================================================

Given an n × n matrix representing an image,
rotate the image by 90 degrees clockwise.

The rotation must be performed in-place,
which means no extra matrix should be created.

Example:

Input:

1 2 3
4 5 6
7 8 9

Output:

7 4 1
8 5 2
9 6 3

===============================================================================
Intuition
===============================================================================

Instead of moving every element individually,

we can rotate the matrix in two simple steps:

1. Transpose the matrix.
2. Reverse every row.

These two operations together produce a 90° clockwise rotation.

===============================================================================
Algorithm
===============================================================================

Step 1:

Transpose the matrix.

Swap

matrix[i][j]

with

matrix[j][i]

------------------------------------------------------------

Step 2:

Reverse every row.

Swap the first and last elements,
second and second-last elements,
and continue until the middle.

===============================================================================
Dry Run
===============================================================================

Input:

1 2 3
4 5 6
7 8 9

------------------------------------------------------------

After Transpose

1 4 7
2 5 8
3 6 9

------------------------------------------------------------

Reverse Every Row

7 4 1
8 5 2
9 6 3

Final Answer

7 4 1
8 5 2
9 6 3

===============================================================================
Important Points to Remember
===============================================================================

1. While Transposing

Always start

j = i + 1

NOT

j = 0

Otherwise every pair will be swapped twice,
and the matrix becomes unchanged.

------------------------------------------------------------

2. While Reversing

Reverse only half of every row.

Loop:

j < n / 2

NOT

j < n

Otherwise every pair will again be swapped twice.

------------------------------------------------------------

3. This approach works only for a Square Matrix (n × n).

===============================================================================
Interview Follow-up
===============================================================================

Q1. Why do we start j from i + 1 while transposing?

Answer:

Because elements below the diagonal have already been swapped.

Starting from i + 1 avoids swapping the same pair twice.

------------------------------------------------------------

Q2. Why do we reverse only half of each row?

Answer:

Each swap fixes two elements.

Reversing beyond the middle would swap them back.

------------------------------------------------------------

Q3. Can we rotate the matrix anti-clockwise?

Yes.

Method:

1. Transpose the matrix.
2. Reverse every column.

===============================================================================
Real-World Applications
===============================================================================

1. Image Processing

Photo editing applications rotate images
using matrix transformations similar to this algorithm.

------------------------------------------------------------

2. Computer Graphics & Game Development

Used to rotate game boards, sprites, maps,
and graphical objects without creating extra memory.

===============================================================================
Pattern
===============================================================================

Pattern Name:

Matrix Transformation

Other Problems Using This Pattern:

• Set Matrix Zeroes
• Spiral Matrix
• Spiral Matrix II
• Reshape Matrix
• Transpose Matrix
• Game of Life

===============================================================================
Key Takeaways
===============================================================================

✔ Rotate 90° Clockwise

= Transpose + Reverse Every Row

✔ Transpose

Swap only the Upper Triangle.

Loop:

j = i + 1

✔ Reverse

Reverse only Half of each Row.

Loop:

j < n / 2

✔ Time Complexity : O(n²)

✔ Space Complexity : O(1)

===============================================================================
*/
