class Solution {

    // Helper method to reverse the array from 'start' to 'end'
    public void reverse(int[] nums, int start, int end) {

        while (start <= end) {

            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;

            start++;
            end--;
        }
    }

    public void nextPermutation(int[] nums) {

        // Step 1:
        // Traverse from right to left and find the first element
        // that is smaller than its next element.
        // This index is called the "pivot".
        int j = nums.length - 1;
        int pivot = -1;

        for (int i = nums.length - 2; i >= 0; i--) {

            if (nums[i] < nums[j]) {
                pivot = i;
                break;
            }

            j--;
        }

        // Step 2:
        // If no pivot is found, the array is in descending order.
        // It is already the last permutation.
        // Reverse the entire array to get the first (smallest) permutation.
        if (pivot == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        // Step 3:
        // Traverse from the end and find the first element
        // greater than nums[pivot].
        // Swap it with the pivot.
        for (int i = nums.length - 1; i > pivot; i--) {

            if (nums[i] > nums[pivot]) {

                int temp = nums[i];
                nums[i] = nums[pivot];
                nums[pivot] = temp;

                break;
            }
        }

        // Step 4:
        // Reverse the elements after the pivot.
        // This gives the next lexicographically greater permutation.
        reverse(nums, pivot + 1, nums.length - 1);
    }
}  

/*
 * ---------------------------------------------------------------
 * NEXT PERMUTATION - INTUITION
 * ---------------------------------------------------------------
 *
 * Step 1: Find the Pivot
 * ----------------------
 * Traverse the array from right to left and find the first element
 * that is smaller than its next element.
 *
 * This index is called the "Pivot".
 *
 * Example:
 * Input : [1, 2, 5, 4, 3]
 *               ^
 *             Pivot = 2
 *
 * ---------------------------------------------------------------
 * Step 2: If no Pivot is found
 * ----------------------------
 * If no such element exists, the array is in descending order.
 * This means it is already the largest possible permutation.
 *
 * Example:
 * [5, 4, 3, 2, 1]
 *
 * Reverse the entire array to get the smallest permutation.
 *
 * Result:
 * [1, 2, 3, 4, 5]
 *
 * ---------------------------------------------------------------
 * Step 3: Find the Next Greater Element
 * -------------------------------------
 * Traverse from the end of the array and find the first element
 * greater than nums[pivot].
 *
 * Swap that element with the pivot.
 *
 * Example:
 *
 * Before Swap:
 * [1, 2, 5, 4, 3]
 *      ^        ^
 *    Pivot   Next Greater
 *
 * After Swap:
 * [1, 3, 5, 4, 2]
 *
 * ---------------------------------------------------------------
 * Step 4: Reverse the Suffix
 * --------------------------
 * Reverse all elements after the pivot.
 *
 * Why?
 * Because the suffix is in descending order.
 * Reversing it makes it ascending (smallest possible order),
 * giving the next immediate lexicographical permutation.
 *
 * Example:
 *
 * Before Reverse:
 * [1, 3, 5, 4, 2]
 *
 * After Reverse:
 * [1, 3, 2, 4, 5]
 *
 * ---------------------------------------------------------------
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 * ---------------------------------------------------------------
 */
