/*
 * ============================================================================
 * Problem Name  : Find the Duplicate Number
 *
 * Platform      : LeetCode 287
 *
 * Difficulty    : Medium
 *
 * Topic         : Arrays, Linked List, Floyd's Cycle Detection
 *
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 * ============================================================================
 */

class Solution {

    public int findDuplicate(int[] nums) {

        // Initialize both pointers from the first element.
        int slow = nums[0];
        int fast = nums[0];

        // Phase 1:
        // Move slow by one step and fast by two steps.
        // They will eventually meet inside the cycle.
        do {

            slow = nums[slow];
            fast = nums[nums[fast]];

        } while (slow != fast);

        // Phase 2:
        // Move one pointer back to the beginning.
        // Move both pointers one step at a time.
        // The point where they meet is the duplicate number.
        fast = nums[0];

        while (slow != fast) {

            slow = nums[slow];
            fast = nums[fast];
        }

        // Return the duplicate number.
        return slow;
    }
}

/*
===============================================================================
Problem Statement
===============================================================================

Given an array nums containing n + 1 integers where every integer
is in the range [1, n],

there is exactly one duplicate number.

Return the duplicate number.

Constraints:

• Do not modify the array.

• Use only constant extra space.

Example:

Input:

[1,3,4,2,2]

Output:

2

===============================================================================
Intuition
===============================================================================

At first glance, this looks like an array problem.

But the trick is to treat the array as a Linked List.

Think of

Index = Current Node

Value = Next Node

Example

nums = [1,3,4,2,2]

Index

0 1 2 3 4

Value

1 3 4 2 2

Connections

0 → 1

1 → 3

3 → 2

2 → 4

4 → 2

Notice

2 → 4 → 2 → 4 ...

A cycle is formed.

Since there is exactly one duplicate number,

there will always be one cycle.

Finding the duplicate number becomes
the same as finding the entrance of the cycle.

===============================================================================
Algorithm
===============================================================================

Phase 1

Find the meeting point inside the cycle.

• Slow moves one step.

• Fast moves two steps.

Eventually,

they meet inside the cycle.

------------------------------------------------------------

Phase 2

Move one pointer back to the beginning.

Move both pointers one step at a time.

The node where they meet again
is the duplicate number.

===============================================================================
Dry Run
===============================================================================

Input

[1,3,4,2,2]

------------------------------------------------------------

Initial

slow = 1

fast = 1

------------------------------------------------------------

Iteration 1

slow = 3

fast = 2

------------------------------------------------------------

Iteration 2

slow = 2

fast = 2

Both meet.

------------------------------------------------------------

Reset

fast = 1

------------------------------------------------------------

Move Together

slow = 4

fast = 3

------------------------------------------------------------

Next Move

slow = 2

fast = 2

Duplicate Number = 2

===============================================================================
Why Does Floyd's Algorithm Work?
===============================================================================

The duplicate value causes two different indices
to point to the same next index.

This creates a cycle in the virtual linked list.

Floyd's Cycle Detection Algorithm guarantees:

1.

The slow and fast pointers meet somewhere inside the cycle.

2.

Resetting one pointer to the beginning and moving both
one step at a time makes them meet at the cycle's entrance.

That entrance is exactly the duplicate number.

===============================================================================
Mistakes I Made While Solving
===============================================================================

1.

I first tried using the Sum Formula.

Expected Sum - Actual Sum

This does NOT work.

Reason:

The duplicate number may appear more than twice.

Example

[3,3,3,3,3]

The sum formula fails.

------------------------------------------------------------

2.

I initially thought this was only an Array problem.

Actually,

it is a Linked List Cycle Detection problem
hidden inside an array.

------------------------------------------------------------

3.

Remember:

Index = Node

Value = Next Pointer

This observation is the key to solving the problem.

===============================================================================
Interview Follow-up
===============================================================================

Q1.

Why can't we sort the array?

Answer

Sorting modifies the original array.

The problem explicitly forbids modifying it.

------------------------------------------------------------

Q2.

Why not use a HashSet?

Answer

HashSet requires O(n) extra space.

The problem requires O(1) extra space.

------------------------------------------------------------

Q3.

Why does a duplicate create a cycle?

Answer

Since two different indices point to the same value,

multiple paths eventually enter the same sequence,

forming a cycle.

===============================================================================
Real-World Applications
===============================================================================

1. Linked List Cycle Detection

Operating systems and memory managers detect
cycles in linked data structures using Floyd's Algorithm.

------------------------------------------------------------

2. Graph and Network Analysis

Cycle detection techniques are used in routing,
dependency analysis, and graph traversal algorithms.

===============================================================================
Pattern
===============================================================================

Pattern Name

Floyd's Tortoise and Hare (Cycle Detection)

Other Problems

• Linked List Cycle

• Linked List Cycle II

• Happy Number

• Find the Duplicate Number

===============================================================================
Key Takeaways
===============================================================================

✔ Treat the array like a Linked List.

✔ Index = Current Node.

✔ Value = Next Node.

✔ Duplicate Number = Entrance of the Cycle.

✔ Floyd's Algorithm finds the cycle
without modifying the array.

✔ Time Complexity : O(n)

✔ Space Complexity : O(1)

===============================================================================
*/
