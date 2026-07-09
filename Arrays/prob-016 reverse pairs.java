/*
 * ============================================================================
 *                         REVERSE PAIRS
 * ============================================================================
 * Difficulty : Hard
 *
 * Time Complexity  : O(n log n)
 * Space Complexity : O(n)
 *
 * ============================================================================
 * Problem Statement
 * ============================================================================
 *
 * Given an integer array nums, return the number of reverse pairs.
 *
 * A reverse pair is defined as:
 *
 *      i < j
 *      nums[i] > 2 * nums[j]
 *
 * Example:
 *
 * Input:
 * [1,3,2,3,1]
 *
 * Output:
 * 2
 *
 * Reverse Pairs:
 *
 * (3,1)
 * (3,1)
 *
 * ============================================================================
 * Intuition
 * ============================================================================
 *
 * A brute-force solution checks every pair,
 * resulting in O(n²) time.
 *
 * Since the question asks for a faster solution,
 * we use Merge Sort.
 *
 * Merge Sort divides the array into smaller sorted halves.
 *
 * Before merging,
 * we count the reverse pairs between the left half
 * and the right half.
 *
 * ============================================================================
 */

import java.util.*;

class Solution {

    public int reversePairs(int[] nums) {

        return mergeSort(nums, 0, nums.length - 1);
    }

    //=========================================================================
    // Merge Sort
    //=========================================================================

    public int mergeSort(int[] nums, int low, int high) {

        // Base Case
        if (low >= high)
            return 0;

        int mid = (low + high) / 2;

        int count = 0;

        // Count reverse pairs in left half
        count += mergeSort(nums, low, mid);

        // Count reverse pairs in right half
        count += mergeSort(nums, mid + 1, high);

        // Count reverse pairs between left and right halves
        count += countPairs(nums, low, mid, high);

        // Merge both sorted halves
        merge(nums, low, mid, high);

        return count;
    }

    //=========================================================================
    // Count Reverse Pairs
    //=========================================================================

    public int countPairs(int[] nums, int low, int mid, int high) {

        int count = 0;

        int right = mid + 1;

        // Traverse every element in the left half
        for (int left = low; left <= mid; left++) {

            // Move right pointer while reverse pair condition is true
            while (right <= high &&
                    nums[left] > 2L * nums[right]) {

                right++;
            }

            // Count all valid elements
            count += right - (mid + 1);
        }

        return count;
    }

    //=========================================================================
    // Merge Two Sorted Arrays
    //=========================================================================

    public void merge(int[] nums, int low, int mid, int high) {

        ArrayList<Integer> temp = new ArrayList<>();

        int left = low;
        int right = mid + 1;

        while (left <= mid && right <= high) {

            if (nums[left] <= nums[right]) {

                temp.add(nums[left]);
                left++;
            } else {

                temp.add(nums[right]);
                right++;
            }
        }

        // Copy remaining left elements
        while (left <= mid) {

            temp.add(nums[left]);
            left++;
        }

        // Copy remaining right elements
        while (right <= high) {

            temp.add(nums[right]);
            right++;
        }

        /*
         * Copy the merged elements back to the original array.
         *
         * temp always starts from index 0,
         * but the original array starts from index 'low'.
         *
         * Therefore,
         *
         * nums[i] = temp.get(i-low)
         */

        for (int i = low; i <= high; i++) {

            nums[i] = temp.get(i - low);
        }
    }
}

/*
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Input:
 *
 * [1,3,2,3,1]
 *
 * After dividing:
 *
 * Left  = [1,3,2]
 * Right = [3,1]
 *
 * After sorting:
 *
 * Left  = [1,2,3]
 * Right = [1,3]
 *
 * Now count reverse pairs.
 *
 * Check every element in Left.
 *
 * left = 1
 *
 * 1 > 2×1 ?
 *
 * 1 > 2
 *
 * No
 *
 * --------------------------
 *
 * left = 2
 *
 * 2 > 2×1 ?
 *
 * 2 > 2
 *
 * No
 *
 * --------------------------
 *
 * left = 3
 *
 * 3 > 2×1 ?
 *
 * 3 > 2
 *
 * Yes
 *
 * Count = 1
 *
 * Continue merging.
 *
 * Total Reverse Pairs = 2
 *
 * ============================================================================
 * Why don't we reset the right pointer?
 * ============================================================================
 *
 * Both halves are already sorted.
 *
 * Once the right pointer moves forward,
 * there is no need to move it backward.
 *
 * This makes counting O(n).
 *
 * ============================================================================
 * Difference from Inversion Count
 * ============================================================================
 *
 * Inversion Count
 *
 * Condition:
 *
 * nums[left] > nums[right]
 *
 * Count:
 *
 * count += (mid-left+1)
 *
 * -------------------------------------------------
 *
 * Reverse Pairs
 *
 * Condition:
 *
 * nums[left] > 2 * nums[right]
 *
 * Since multiplication is involved,
 * we cannot directly count all remaining elements.
 *
 * We move the right pointer separately
 * and count:
 *
 * count += right-(mid+1)
 *
 * ============================================================================
 * Common Mistakes I Made
 * ============================================================================
 *
 * ❌ Copied merged elements like this:
 *
 * for(int i=0;i<temp.size();i++)
 *     nums[i]=temp.get(i);
 *
 * This overwrites the beginning of the array.
 *
 * -------------------------------------------------
 *
 * ✅ Correct:
 *
 * for(int i=low;i<=high;i++)
 *     nums[i]=temp.get(i-low);
 *
 * -------------------------------------------------
 *
 * ❌ Forgot to use 2L.
 *
 * nums[left] > 2L * nums[right]
 *
 * Using 2L prevents integer overflow.
 *
 * -------------------------------------------------
 *
 * ❌ Merged first and counted later.
 *
 * Always:
 *
 * Count
 * ↓
 * Merge
 *
 * ============================================================================
 * Interview Follow-up
 * ============================================================================
 *
 * Q) Why is Merge Sort used?
 *
 * Because Merge Sort provides two sorted halves,
 * allowing us to count reverse pairs efficiently
 * using two pointers.
 *
 * ============================================================================
 * Real-World Applications
 * ============================================================================
 *
 * 1. Financial Analysis
 * ---------------------
 * Detect unusually large drops or spikes
 * between earlier and later stock prices.
 *
 * -------------------------------------------------
 *
 * 2. Data Analytics
 * -----------------
 * Used to identify abnormal relationships
 * between historical and current data.
 *
 * ============================================================================
 * Pattern to Remember
 * ============================================================================
 *
 * Merge Sort
 *
 * Divide
 *      ↓
 * Solve Left
 *      ↓
 * Solve Right
 *      ↓
 * Count Answer
 *      ↓
 * Merge
 *
 * Many interview problems follow this pattern:
 *
 * ✔ Inversion Count
 * ✔ Reverse Pairs
 * ✔ Count Smaller Numbers After Self
 *
 * ============================================================================
 */
