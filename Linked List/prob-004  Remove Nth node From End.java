/*
 * ============================================================================
 * Problem: Remove Nth Node From End of List
 * Difficulty: Medium
 * LeetCode: 19
 * ============================================================================
 *
 * Problem Statement:
 * Given the head of a linked list, remove the nth node from the end of the list
 * and return its head.
 *
 * Example:
 * Input: 1 -> 2 -> 3 -> 4 -> 5, n = 2
 * Output: 1 -> 2 -> 3 -> 5
 *
 * ============================================================================
 * Brute Force Approach (Two Traversals)
 * ============================================================================
 *
 * Idea:
 * 1. Count the total number of nodes.
 * 2. Find the (length - n)th node.
 * 3. Delete its next node.
 *
 * Time Complexity : O(2N) ≈ O(N)
 * Space Complexity: O(1)
 */

class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode temp = head;
        int count = 0;

        // Count total nodes
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        // If head needs to be deleted
        if (count == n)
            return head.next;

        temp = head;

        // Move to the node before the one to delete
        for (int i = 1; i < count - n; i++) {
            temp = temp.next;
        }

        // Delete node
        temp.next = temp.next.next;

        return head;
    }
}

/*
 * ============================================================================
 * Optimal Approach (Fast & Slow Pointer)
 * ============================================================================
 *
 * Idea:
 * - Move the fast pointer 'n' steps ahead.
 * - Keep the slow pointer at the head.
 * - Move both pointers together until fast reaches the last node.
 * - Slow will now be exactly before the node that needs to be deleted.
 *
 * Time Complexity : O(N)
 * Space Complexity: O(1)
 */

class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode slow = head;
        ListNode fast = head;

        // Move fast pointer n steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Delete the head node
        if (fast == null)
            return head.next;

        // Move both pointers together
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove nth node from end
        slow.next = slow.next.next;

        return head;
    }
}

/*
 * ============================================================================
 * Dry Run (Optimal Approach)
 * ============================================================================
 *
 * Example:
 *
 * 1 -> 2 -> 3 -> 4 -> 5
 * n = 2
 *
 * Initially
 *
 * S
 * |
 * v
 * 1 -> 2 -> 3 -> 4 -> 5
 * ^
 * |
 * F
 *
 * ------------------------------------------------
 * Move fast 2 steps
 *
 * S
 * |
 * v
 * 1 -> 2 -> 3 -> 4 -> 5
 *          ^
 *          F
 *
 * Gap = 2 nodes
 *
 * ------------------------------------------------
 * Move both together
 *
 * Step 1
 *
 * 1 -> 2 -> 3 -> 4 -> 5
 *      S       F
 *
 * Step 2
 *
 * 1 -> 2 -> 3 -> 4 -> 5
 *          S       F
 *
 * Step 3
 *
 * 1 -> 2 -> 3 -> 4 -> 5
 *              S       F(last)
 *
 * Fast reached the last node.
 *
 * Slow is just before the node to delete.
 *
 * Delete:
 *
 * slow.next = slow.next.next;
 *
 * Result:
 *
 * 1 -> 2 -> 3 -> 5
 *
 * ============================================================================
 * Why Fast & Slow Works
 * ============================================================================
 *
 * After moving fast by n nodes, there is always a gap of n nodes
 * between fast and slow.
 *
 * When both pointers move together,
 * this gap never changes.
 *
 * Therefore, when fast reaches the last node,
 * slow is automatically positioned just before
 * the nth node from the end.
 *
 * ============================================================================
 * Common Mistakes I Made
 * ============================================================================
 *
 * 1. Forgot to move temp while counting nodes.
 *
 *      while(temp != null){
 *          count++;
 *      }
 *
 *    Infinite loop.
 *
 * ------------------------------------------------
 *
 * 2. Forgot to reset temp back to head after counting.
 *
 * ------------------------------------------------
 *
 * 3. Forgot to handle deleting the first node.
 *
 *      if(count == n)
 *          return head.next;
 *
 * ------------------------------------------------
 *
 * 4. Didn't understand why fast is moved first.
 *
 *    Moving fast first creates a fixed gap of n nodes.
 *
 * ------------------------------------------------
 *
 * 5. Initially tried to solve using only counting.
 *
 *    Later learned the Fast & Slow Pointer technique.
 *
 * ============================================================================
 * Patterns Learned
 * ============================================================================
 *
 * Brute Force Pattern
 *
 * Count Length
 * ↓
 * Go to (Length - n)
 * ↓
 * Delete
 *
 * ------------------------------------------------
 *
 * Optimal Pattern
 *
 * Fast += n steps
 * ↓
 * Move Fast & Slow together
 * ↓
 * Delete Slow.next
 *
 * ============================================================================
 * Similar Problems
 * ============================================================================
 *
 * ✓ Middle of Linked List
 * ✓ Remove Nth Node From End
 * ✓ Linked List Cycle
 * ✓ Linked List Cycle II
 * ✓ Find Kth Node From End
 * ✓ Palindrome Linked List
 * ✓ Reorder List
 * ✓ Sort List
 *
 * ============================================================================
 * Backend Java / Spring Boot Uses
 * ============================================================================
 *
 * Although this exact problem isn't implemented directly,
 * the two-pointer idea is widely used in backend development:
 *
 * • Processing streaming data with two moving cursors.
 * • Maintaining a fixed-size sliding window.
 * • Rate limiting algorithms.
 * • Queue processing.
 * • Detecting loops in workflows.
 * • Efficient traversal without extra memory.
 *
 * ============================================================================
 * Interview Tip
 * ============================================================================
 *
 * If the interviewer says:
 *
 * - Remove kth node from end
 * - Find kth node from end
 * - Delete nth node from end
 *
 * Immediately think:
 *
 * FAST & SLOW POINTER
 *
 * If the interviewer asks for an easier approach first,
 * explain the brute-force counting method and then optimize
 * it to the one-pass solution.
 * ============================================================================
 */
