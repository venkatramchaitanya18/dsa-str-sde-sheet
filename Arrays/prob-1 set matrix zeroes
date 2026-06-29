//optimal soluition
class Solution {
    public void setZeroes(int[][] matrix) {

        // col0 is used to track whether the first column needs to be made zero.
        // We can't use matrix[0][0] for this because it is already used to track the first row.
        int col0 = 1;

        // -------------------- Step 1 --------------------
        // Traverse the matrix and use the first row & first column as markers.
        // If matrix[i][j] == 0:
        //   - Mark its row by setting matrix[i][0] = 0
        //   - Mark its column by setting matrix[0][j] = 0
        //   - If the zero is in the first column, update col0 instead.
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] == 0) {

                    // Mark the current row
                    matrix[i][0] = 0;

                    // Mark the current column
                    if (j != 0) {
                        matrix[0][j] = 0;
                    } else {
                        // Remember that the first column also needs to become zero
                        col0 = 0;
                    }
                }
            }
        }

        // -------------------- Step 2 --------------------
        // Ignore the first row and first column for now.
        // Use the markers to update the remaining cells.
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {

                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // -------------------- Step 3 --------------------
        // If matrix[0][0] is 0, the entire first row should become zero.
        if (matrix[0][0] == 0) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }

        // -------------------- Step 4 --------------------
        // If col0 is 0, the entire first column should become zero.
        if (col0 == 0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
