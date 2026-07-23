/*
 * ============================================================================
 * Problem: Middle of the Linked List
 * Difficulty: Easy
 * LeetCode: 876
 * ============================================================================
 *
 * Problem Statement:
 * Given the head of a singly linked list, return the middle node.
 *
 * If there are two middle nodes, return the second middle node.
 *
 * Example:
 * Input: 1 -> 2 -> 3 -> 4 -> 5
 * Output: 3
 *
 * Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6
 * Output: 4
 *
 * ============================================================================
 * Optimal Approach: Fast & Slow Pointer
 * ============================================================================
 *
 * Idea:
 * - slow moves one step at a time
 * - fast moves two steps at a time
 *
 * When fast reaches the end,
 * slow will be exactly at the middle node.
 *
 * Why it works:
 * - fast covers the list twice as quickly as slow
 * - by the time fast travels the entire list,
 *   slow has traveled half of it
 *
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        // Move slow by 1 step and fast by 2 steps
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        // slow points to the middle node
        return slow;
    }
}

/*
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Example 1:
 * 1 -> 2 -> 3 -> 4 -> 5
 *
 * Initially:
 * slow = 1
 * fast = 1
 *
 * Iteration 1:
 * slow = 2
 * fast = 3
 *
 * Iteration 2:
 * slow = 3
 * fast = 5
 *
 * Iteration 3:
 * fast.next == null
 * loop stops
 *
 * Return slow = 3
 *
 * --------------------------------------------------------------------------
 *
 * Example 2:
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6
 *
 * Initially:
 * slow = 1
 * fast = 1
 *
 * Iteration 1:
 * slow = 2
 * fast = 3
 *
 * Iteration 2:
 * slow = 3
 * fast = 5
 *
 * Iteration 3:
 * slow = 4
 * fast = null
 *
 * Return slow = 4
 *
 * LeetCode expects the second middle node,
 * so this is correct.
 *
 * ============================================================================
 * Common Mistakes I Made While Solving
 * ============================================================================
 *
 * 1. Used:
 *
 *    while (fast.next.next != null)
 *
 *    This causes a NullPointerException
 *    when fast or fast.next becomes null.
 *
 * --------------------------------------------------------------------------
 *
 * 2. Returned the wrong middle for even-length lists.
 *
 *    The condition above stops too early
 *    and returns the first middle instead of the second middle.
 *
 * --------------------------------------------------------------------------
 *
 * 3. Created an unnecessary temporary pointer:
 *
 *    ListNode temp = head;
 *
 *    It was never used.
 *
 * --------------------------------------------------------------------------
 *
 * Correct condition:
 *
 *    while (fast != null && fast.next != null)
 *
 * This safely allows:
 *
 *    fast = fast.next.next;
 *
 * ============================================================================
 * Fast & Slow Pointer Pattern
 * ============================================================================
 *
 * This exact pattern is reused in many linked list problems:
 *
 * ✓ Middle of Linked List
 * ✓ Linked List Cycle
 * ✓ Linked List Cycle II
 * ✓ Palindrome Linked List
 * ✓ Reorder List
 * ✓ Sort List (Merge Sort)
 * ✓ Split Linked List into Parts
 *
 * Pattern:
 *
 *    while (fast != null && fast.next != null) {
 *        slow = slow.next;
 *        fast = fast.next.next;
 *    }
 *
 * ============================================================================
 * Backend Java / Spring Boot Uses
 * ============================================================================
 *
 * The fast & slow pointer idea appears in:
 *
 * - Streaming data processing
 * - Rate limiting algorithms
 * - Detecting loops in workflows
 * - Monitoring circular task queues
 * - Memory-efficient traversal of large linked structures
 *
 * ============================================================================
 * Interview Tip
 * ============================================================================
 *
 * Whenever a linked list problem asks:
 * - middle node
 * - cycle detection
 * - palindrome
 * - split the list
 *
 * think immediately:
 *
 *     FAST & SLOW POINTER
 *
 * This is one of the most frequently asked linked list patterns.
 * ============================================================================
 */
