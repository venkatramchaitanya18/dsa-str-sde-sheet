/*
 * Problem: Merge Two Sorted Lists
 * Difficulty: Easy
 *
 * Approach:
 * - Create a dummy node to simplify building the merged linked list.
 * - Use a pointer 'temp' to construct the merged list.
 * - Compare the current nodes of both lists.
 * - Attach the smaller node to the merged list.
 * - Move the corresponding list pointer forward.
 * - Move 'temp' forward.
 * - Once one list becomes empty, attach the remaining nodes of the other list.
 * - Return dummy.next because dummy is just a placeholder node.
 *
 * Time Complexity: O(n + m)
 * Space Complexity: O(1)
 *
 * --------------------------------------------------------------------
 * Why Dummy Node?
 * --------------------------------------------------------------------
 * The dummy node acts as a fixed starting point.
 * It eliminates special cases such as:
 * - First node insertion
 * - Empty list handling
 * - Updating the head repeatedly
 *
 * After merging:
 *
 * dummy
 *   |
 *  -1 -> 1 -> 2 -> 3 -> 4 -> 5
 *
 * Return:
 * dummy.next
 *
 * --------------------------------------------------------------------
 * Pointer Movement
 * --------------------------------------------------------------------
 * list1 -> Current node of first list
 * list2 -> Current node of second list
 * temp  -> Last node of merged list
 *
 * --------------------------------------------------------------------
 * Dry Run
 * --------------------------------------------------------------------
 * List1:
 * 1 -> 3 -> 5
 *
 * List2:
 * 2 -> 4 -> 6
 *
 * Initially:
 *
 * dummy(-1)
 *    |
 *   temp
 *
 * ------------------------------------------------
 * Compare 1 and 2
 *
 * 1 is smaller
 *
 * dummy -> 1
 *
 * temp = 1
 * list1 = 3
 *
 * ------------------------------------------------
 * Compare 3 and 2
 *
 * 2 is smaller
 *
 * dummy -> 1 -> 2
 *
 * temp = 2
 * list2 = 4
 *
 * ------------------------------------------------
 * Compare 3 and 4
 *
 * Attach 3
 *
 * dummy -> 1 -> 2 -> 3
 *
 * ------------------------------------------------
 * Compare 5 and 4
 *
 * Attach 4
 *
 * dummy -> 1 -> 2 -> 3 -> 4
 *
 * ------------------------------------------------
 * Compare 5 and 6
 *
 * Attach 5
 *
 * dummy -> 1 -> 2 -> 3 -> 4 -> 5
 *
 * list1 becomes null
 *
 * ------------------------------------------------
 * Attach remaining list2
 *
 * dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
 *
 * Return:
 * dummy.next
 *
 * --------------------------------------------------------------------
 * Common Mistakes I Made While Solving
 * --------------------------------------------------------------------
 * 1. Started temp as null.
 *
 *    ListNode temp = null;
 *
 *    Then writing:
 *
 *    temp.next = ...
 *
 *    causes a NullPointerException.
 *
 * ------------------------------------------------
 *
 * 2. Created a new node but never used it.
 *
 *    ListNode ll = new ListNode();
 *
 *    temp was never assigned to ll.
 *
 * ------------------------------------------------
 *
 * 3. Tried copying values instead of connecting nodes.
 *
 *    temp.val = list1.val;
 *
 *    This is unnecessary.
 *    Reuse the existing linked list nodes.
 *
 * ------------------------------------------------
 *
 * 4. Forgot that temp.next is initially null.
 *
 *    temp = temp.next;
 *
 *    Without creating a new node,
 *    temp becomes null.
 *
 * ------------------------------------------------
 *
 * 5. Forgot the Dummy Node pattern.
 *
 *    Dummy node removes all edge cases.
 *
 * --------------------------------------------------------------------
 * Pattern Learned
 * --------------------------------------------------------------------
 * Whenever you need to CREATE or BUILD a linked list:
 *
 * ListNode dummy = new ListNode(-1);
 * ListNode temp = dummy;
 *
 * Keep attaching nodes using:
 *
 * temp.next = node;
 * temp = temp.next;
 *
 * Return:
 *
 * dummy.next;
 *
 * --------------------------------------------------------------------
 * Similar Problems Using This Pattern
 * --------------------------------------------------------------------
 * ✓ Merge Two Sorted Lists
 * ✓ Merge K Sorted Lists
 * ✓ Add Two Numbers
 * ✓ Partition List
 * ✓ Remove Duplicates from Sorted List
 * ✓ Reverse Nodes in K Group
 * ✓ Swap Nodes in Pairs
 * ✓ Odd Even Linked List
 * ✓ Reorder List
 *
 * --------------------------------------------------------------------
 * Backend Java / Spring Boot Uses
 * --------------------------------------------------------------------
 * Although LinkedList is rarely used directly in backend services,
 * the merge/build pattern appears frequently.
 *
 * Examples:
 *
 * 1. Merging two sorted database query results.
 *
 * 2. Combining logs from multiple servers in chronological order.
 *
 * 3. Merging paginated REST API responses.
 *
 * 4. Combining event streams (Kafka/RabbitMQ consumers).
 *
 * 5. Building response chains before sending JSON to clients.
 *
 * 6. Implementing custom queue structures.
 *
 * 7. Merge phase of Merge Sort.
 *
 * --------------------------------------------------------------------
 * Interview Pattern Recognition
 * --------------------------------------------------------------------
 * If the problem says:
 *
 * - Merge two linked lists
 * - Build a new linked list
 * - Keep original nodes
 * - Attach nodes one by one
 *
 * Think immediately:
 *
 * Dummy Node + temp pointer
 *
 * This is one of the most frequently used linked list patterns.
 */

class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;

        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }

            temp = temp.next;
        }

        while (list1 != null) {
            temp.next = list1;
            list1 = list1.next;
            temp = temp.next;
        }

        while (list2 != null) {
            temp.next = list2;
            list2 = list2.next;
            temp = temp.next;
        }

        return dummy.next;
    }
}
