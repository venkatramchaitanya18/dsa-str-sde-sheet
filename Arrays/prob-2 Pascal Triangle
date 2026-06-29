package DSA.arrays;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    /*
     * -------------------------------------------------------------
     * Type 1:
     * Given a row and column, return the value at that position.
     *
     * Formula:
     *          nCr = n! / (r! * (n-r)!)
     *
     * Instead of calculating factorials (which is costly),
     * we calculate nCr iteratively.
     *
     * Formula used:
     *
     *      ans = ans * (n - i) / i
     *
     * Time Complexity : O(col)
     * Space Complexity: O(1)
     * -------------------------------------------------------------
     */

    public static int getValue(int row, int col) {

        int ans = 1;

        // Calculate (row-1)C(col-1)
        for (int i = 1; i < col; i++) {
            ans = ans * (row - i);
            ans = ans / (col - i);
        }

        return ans;
    }

    /*
     * -------------------------------------------------------------
     * Type 2:
     * Given a row number, print the entire row.
     *
     * Example:
     *
     * Row = 5
     * Output:
     * [1,4,6,4,1]
     *
     * Observation:
     *
     * First element is always 1.
     *
     * Every next element can be generated from the previous one
     * using the nCr formula.
     *
     * Time Complexity : O(n)
     * Space Complexity: O(n)
     * -------------------------------------------------------------
     */

    public static List<Integer> getRow(int n) {

        List<Integer> row = new ArrayList<>();

        int ans = 1;

        row.add(1);

        for (int i = 1; i < n; i++) {

            ans = ans * (n - i);
            ans = ans / i;

            row.add(ans);
        }

        return row;
    }

    /*
     * -------------------------------------------------------------
     * Type 3:
     * Print the complete Pascal Triangle.
     *
     * Idea:
     * Generate every row one by one using getRow().
     *
     * Example:
     *
     * numRows = 5
     *
     * [
     * [1]
     * [1,1]
     * [1,2,1]
     * [1,3,3,1]
     * [1,4,6,4,1]
     * ]
     *
     * Time Complexity : O(n²)
     * Space Complexity: O(n²)
     * -------------------------------------------------------------
     */

    public static List<List<Integer>> printPascalTriangle(int numRows) {

        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 1; i <= numRows; i++) {

            triangle.add(getRow(i));

        }

        return triangle;
    }

    public static void main(String[] args) {

        // Type 1
        int row = 4;
        int col = 2;

        System.out.println(getValue(row, col));

        // Type 2
        int n = 5;

        System.out.println(getRow(n));

        // Type 3
        int numRows = 5;

        System.out.println(printPascalTriangle(numRows));
    }
}
