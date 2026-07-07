/*
 * ============================================================================
 *                    229. Majority Element II
 * ============================================================================
 *
 * Difficulty : Medium
 *
 * Topic      : Boyer-Moore Voting Algorithm (Extended)
 *
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 *
 * ============================================================================
 * Problem Statement
 * ============================================================================
 *
 * Given an integer array nums of size n,
 * return all elements that appear more than ⌊n/3⌋ times.
 *
 * There can be at most TWO such elements.
 *
 * Example 1:
 *
 * Input  : [3,2,3]
 * Output : [3]
 *
 * Example 2:
 *
 * Input  : [1,1,1,3,3,2,2,2]
 * Output : [1,2]
 *
 * ============================================================================
 * Intuition
 * ============================================================================
 *
 * For Majority Element (> n/2),
 * there can be only ONE majority element.
 *
 * For Majority Element II (> n/3),
 * there can be AT MOST TWO majority elements.
 *
 * Therefore,
 * we maintain two candidates and two counters.
 *
 * Whenever we encounter a new element:
 *
 * • If it matches candidate1 -> increase count1.
 * • Else if it matches candidate2 -> increase count2.
 * • Else if count1 becomes 0 -> choose a new candidate1.
 * • Else if count2 becomes 0 -> choose a new candidate2.
 * • Otherwise, decrease both counters.
 *
 * This process removes groups of three different elements,
 * leaving only possible majority candidates.
 *
 * ============================================================================
 * Algorithm
 * ============================================================================
 *
 * Step 1:
 * ----------
 * Maintain
 *
 * candidate1
 * candidate2
 *
 * count1
 * count2
 *
 * --------------------------------------------------------------------------
 *
 * Step 2:
 * ----------
 * Traverse the array once.
 *
 * Update candidates and counts using Boyer-Moore rules.
 *
 * --------------------------------------------------------------------------
 *
 * Step 3:
 * ----------
 * The first pass only finds POSSIBLE candidates.
 *
 * Traverse the array again
 * and count the actual frequencies.
 *
 * --------------------------------------------------------------------------
 *
 * Step 4:
 * ----------
 * Add every candidate whose frequency
 * is greater than n/3.
 *
 * ============================================================================
 */

class Solution {

    public List<Integer> majorityElement(int[] nums) {

        // First candidate
        int curr = nums[0];

        // Second candidate
        int prev = 0;

        // Vote count of first candidate
        int count1 = 1;

        // Vote count of second candidate
        int count2 = 0;

        // First Pass : Find two possible candidates
        for (int i = 1; i < nums.length; i++) {

            // Same as first candidate
            if (nums[i] == curr) {
                count1++;
            }

            // Same as second candidate
            else if (nums[i] == prev) {
                count2++;
            }

            // First candidate lost all votes
            else if (count1 == 0) {
                curr = nums[i];
                count1 = 1;
            }

            // Second candidate lost all votes
            else if (count2 == 0) {
                prev = nums[i];
                count2 = 1;
            }

            // Different from both candidates
            // Remove one vote from each
            else {
                count1--;
                count2--;
            }
        }

        // Verify the actual frequencies
        count1 = 0;
        count2 = 0;

        for (int num : nums) {

            if (num == curr)
                count1++;

            else if (num == prev)
                count2++;
        }

        List<Integer> ans = new ArrayList<>();

        if (count1 > nums.length / 3)
            ans.add(curr);

        if (curr != prev && count2 > nums.length / 3)
            ans.add(prev);

        return ans;
    }
}

/*
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Input
 *
 * [1,1,1,3,3,2,2,2]
 *
 * n = 8
 *
 * n/3 = 2
 *
 * ----------------------------------------------------
 *
 * Candidate1 = 1
 * Count1 = 1
 *
 * Candidate2 = 0
 * Count2 = 0
 *
 * ----------------------------------------------------
 *
 * 1 -> Count1 = 2
 *
 * 1 -> Count1 = 3
 *
 * 3 -> Candidate2 = 3
 * Count2 = 1
 *
 * 3 -> Count2 = 2
 *
 * 2 -> Count1--
 * Count2--
 *
 * 2 -> Count1--
 * Count2--
 *
 * 2 -> Candidate2 becomes 2
 *
 * ----------------------------------------------------
 *
 * Candidates after first pass
 *
 * Candidate1 = 1
 * Candidate2 = 2
 *
 * Verify frequencies
 *
 * 1 occurs 3 times ✔
 *
 * 2 occurs 3 times ✔
 *
 * Answer
 *
 * [1,2]
 *
 * ============================================================================
 * Why Second Pass?
 * ============================================================================
 *
 * The first traversal DOES NOT calculate
 * the actual frequencies.
 *
 * It only removes unnecessary elements
 * and keeps possible majority candidates.
 *
 * Therefore,
 * we must verify the candidates
 * by counting their actual occurrences.
 *
 * ============================================================================
 * Time Complexity
 * ============================================================================
 *
 * First Traversal  : O(n)
 *
 * Second Traversal : O(n)
 *
 * Overall          : O(n)
 *
 * Space Complexity : O(1)
 *
 * ============================================================================
 * Interview Follow-up
 * ============================================================================
 *
 * Q1. Why do we need two candidates?
 *
 * Because there can be at most two elements
 * appearing more than n/3 times.
 *
 * Example:
 *
 * n = 9
 *
 * Frequencies
 *
 * 4,4,1
 *
 * Two elements satisfy the condition.
 *
 * Three majority elements are impossible because
 *
 * 4 + 4 + 4 = 12 > 9.
 *
 * --------------------------------------------------------------------------
 *
 * Q2. Can we solve this using HashMap?
 *
 * Yes.
 *
 * Time  : O(n)
 *
 * Space : O(n)
 *
 * Boyer-Moore is preferred because it uses O(1) space.
 *
 * ============================================================================
 * Real-World Applications
 * ============================================================================
 *
 * 1. Election Systems
 *
 * Used to find candidates receiving a dominant share
 * of votes while using constant memory.
 *
 * --------------------------------------------------------------------------
 *
 * 2. Data Stream Processing
 *
 * Used to identify frequently occurring events,
 * error codes, user IDs, or transactions
 * without storing the entire frequency table.
 *
 * ============================================================================
 * Pattern Recognition
 * ============================================================================
 *
 * More than n/2  -> 1 Candidate
 *
 * More than n/3  -> 2 Candidates
 *
 * More than n/k  -> (k - 1) Candidates
 *
 * This is the generalized Boyer-Moore Voting Algorithm.
 *
 * ============================================================================
 * Similar Problems
 * ============================================================================
 *
 * ✔ 169. Majority Element
 *
 * ✔ 229. Majority Element II
 *
 * ✔ Find All Elements Appearing More Than n/k Times
 *
 * ✔ Heavy Hitters Problem
 *
 * ✔ Boyer-Moore Voting Algorithm Variants
 *
 * ============================================================================
 *
 * Mistakes I Made While Solving
 * ============================================================================
 *
 * 1. Tried extending the n/2 solution using only one candidate.
 *    -> The n/3 problem requires TWO candidates and TWO counters.
 *
 * 2. Compared vote counts with n/3 during the first traversal.
 *    -> Vote counts are NOT actual frequencies.
 *
 * 3. Forgot the verification (second pass).
 *    -> The first pass only finds possible candidates.
 *
 * 4. Used >= instead of >.
 *    -> The problem asks for elements appearing MORE THAN n/3 times.
 *
 * 5. Assigned index instead of value.
 *
 *    Wrong:
 *        curr = i;
 *
 *    Correct:
 *        curr = nums[i];
 *
 * 6. Forgot to avoid duplicate answers when both candidates become equal.
 *
 * ============================================================================
 */
