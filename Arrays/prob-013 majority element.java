
/*
 * ============================================================================
 *                     169. Majority Element
 * ============================================================================
 *
 * Difficulty : Easy
 *
 * Topic      : Moore's Voting Algorithm
 *
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 *
 * ============================================================================
 * Problem Statement
 * ============================================================================
 *
 * Given an integer array nums of size n,
 * return the majority element.
 *
 * The majority element is the element that appears
 * more than ⌊n/2⌋ times.
 *
 * It is guaranteed that the majority element always exists.
 *
 * Example
 *
 * Input  : [2,2,1,1,1,2,2]
 * Output : 2
 *
 * ============================================================================
 * Intuition
 * ============================================================================
 *
 * Think of every different element as cancelling one occurrence
 * of the current candidate.
 *
 * If two different elements appear,
 * they cancel each other.
 *
 * Since the majority element appears more than n/2 times,
 * it can never be completely cancelled.
 *
 * Therefore, the remaining candidate is the majority element.
 *
 * ============================================================================
 * Algorithm
 * ============================================================================
 *
 * Step 1
 * ----------
 * Assume the first element is the candidate.
 *
 * candidate = nums[0]
 *
 * count = 1
 *
 * ---------------------------------------------------------------------------
 *
 * Step 2
 * ----------
 * Traverse the remaining array.
 *
 * If the current element equals the candidate,
 * increase the count.
 *
 * Otherwise,
 * decrease the count.
 *
 * ---------------------------------------------------------------------------
 *
 * Step 3
 * ----------
 * Whenever count becomes 0,
 * choose the current element as the new candidate
 * and reset count to 1.
 *
 * ============================================================================
 */

class Solution {

    public int majorityElement(int[] nums) {

        // Assume the first element is the majority candidate
        int ele = nums[0];

        // Count of the current candidate
        int count = 1;

        // Traverse the remaining array
        for (int i = 1; i < nums.length; i++) {

            // Same candidate -> increase count
            if (nums[i] == ele) {
                count++;
            }

            // Count becomes zero,
            // choose a new candidate
            else if (count == 0) {
                ele = nums[i];
                count = 1;
            }

            // Different element -> cancel one occurrence
            else {
                count--;
            }
        }

        // The remaining candidate is the majority element
        return ele;
    }
}

/*
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Input
 *
 * [2,2,1,1,1,2,2]
 *
 * ----------------------------------------------------
 *
 * Candidate = 2
 * Count = 1
 *
 * 2 -> Same
 * Count = 2
 *
 * 1 -> Different
 * Count = 1
 *
 * 1 -> Different
 * Count = 0
 *
 * Count became 0
 *
 * Candidate = 1
 * Count = 1
 *
 * 2 -> Different
 * Count = 0
 *
 * Count became 0
 *
 * Candidate = 2
 * Count = 1
 *
 * End
 *
 * Answer = 2
 *
 * ============================================================================
 * Why Does It Work?
 * ============================================================================
 *
 * Every different element cancels one occurrence
 * of the current candidate.
 *
 * Since the majority element appears more than n/2 times,
 * it cannot be completely cancelled.
 *
 * Therefore,
 * the final candidate is always the majority element.
 *
 * ============================================================================
 * Time Complexity
 * ============================================================================
 *
 * Traverse the array once.
 *
 * Time Complexity : O(n)
 *
 * Space Complexity : O(1)
 *
 * ============================================================================
 * Interview Follow-up
 * ============================================================================
 *
 * Q1. What if the majority element is NOT guaranteed?
 *
 * Moore's Voting Algorithm only gives a candidate.
 *
 * Traverse the array one more time
 * and count its occurrences.
 *
 * If the count is greater than n/2,
 * return it.
 *
 * Otherwise,
 * no majority element exists.
 *
 * ---------------------------------------------------------------------------
 *
 * Q2. Why reset the candidate when count becomes 0?
 *
 * When count reaches 0,
 * all previous elements have been completely cancelled.
 *
 * So the current element becomes a new possible candidate.
 *
 * ============================================================================
 * Real-World Applications
 * ============================================================================
 *
 * 1. Election Voting Systems
 *
 * Used to determine whether a candidate
 * has received more than half of the total votes.
 *
 * ---------------------------------------------------------------------------
 *
 * 2. Data Stream Analysis
 *
 * Used to find the dominant value in a continuous
 * stream of data while using constant memory.
 *
 * ============================================================================
 * Pattern Recognition
 * ============================================================================
 *
 * Whenever you see:
 *
 * ✔ Majority Element
 *
 * ✔ Element occurring more than n/2 times
 *
 * ✔ O(n) time
 *
 * ✔ O(1) extra space
 *
 * Immediately think:
 *
 * Moore's Voting Algorithm
 *
 * ============================================================================
 * Similar Problems
 * ============================================================================
 *
 * ✔ Majority Element
 *
 * ✔ Majority Element II
 *
 * ✔ Find All Elements Appearing More Than n/3 Times
 *
 * ✔ Boyer-Moore Voting Algorithm Variants
 *
 * ============================================================================
 */
