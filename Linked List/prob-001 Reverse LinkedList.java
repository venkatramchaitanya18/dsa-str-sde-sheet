/*
 * Problem: Reverse Linked List
 * Difficulty: Easy
 *
 * Approach:
 * - Use three pointers:
 *   1. temp  -> Current node
 *   2. prev  -> Previous node
 *   3. front -> Next node (to avoid losing the remaining list)
 * - Traverse the list and reverse each link one by one.
 * - At the end, 'prev' points to the new head of the reversed list.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Real-World Uses:
 * - Undo functionality in applications.
 * - Reversing browser history.
 * - Reversing playlists or navigation paths.
 * - Used as a subroutine in many linked list problems
 *   (Palindrome Linked List, Reverse in K Groups, Reorder List, etc.).
 */

class Solution {
    public ListNode reverseList(ListNode head) {

        ListNode temp = head;
        ListNode prev = null;

        while (temp != null) {

            // Store the next node
            ListNode front = temp.next;

            // Reverse the current node's pointer
            temp.next = prev;

            // Move prev and temp one step forward
            prev = temp;
            temp = front;
        }

        // 'prev' becomes the new head
        return prev;
    }
}

/*
-------------------------------- Dry Run --------------------------------

Input:
1 -> 2 -> 3 -> 4 -> null

Initially:
prev = null
temp = 1

Iteration 1:
front = 2
1 -> null
prev = 1
temp = 2

Current List:
1 -> null

Remaining:
2 -> 3 -> 4

------------------------------------------------

Iteration 2:
front = 3
2 -> 1 -> null
prev = 2
temp = 3

------------------------------------------------

Iteration 3:
front = 4
3 -> 2 -> 1 -> null
prev = 3
temp = 4

------------------------------------------------

Iteration 4:
front = null
4 -> 3 -> 2 -> 1 -> null
prev = 4
temp = null

Loop Ends

Output:
4 -> 3 -> 2 -> 1 -> null

--------------------------------------------------------------------------

Key Idea:

Before changing any pointer, always save the next node.

front = temp.next;
temp.next = prev;
prev = temp;
temp = front;

This four-line pattern is the foundation for many linked list problems.
*/
