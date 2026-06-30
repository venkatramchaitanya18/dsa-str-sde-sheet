/*
 * ============================================================================
 * Problem Name  : Sort Colors (Dutch National Flag Algorithm)
 *
 * Platform      : LeetCode 75
 *
 * Difficulty    : Medium
 *
 * Topic         : Arrays, Two Pointers
 *
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 * ============================================================================
 */

class Solution {

    public void sortColors(int[] nums) {

        // low  -> Next position to place 0
        // mid  -> Current element being processed
        // high -> Next position to place 2
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        // Continue until all elements are processed
        while (mid <= high) {

            // If the current element is 0,
            // swap it with the low pointer,
            // then move both low and mid forward.
            if (nums[mid] == 0) {

                int temp = nums[mid];
                nums[mid] = nums[low];
                nums[low] = temp;

                low++;
                mid++;
            }

            // If the current element is 1,
            // it is already in the correct position.
            // Just move the mid pointer.
            else if (nums[mid] == 1) {

                mid++;
            }

            // If the current element is 2,
            // swap it with the high pointer.
            // Do NOT move mid because the swapped element
            // still needs to be checked.
            else {

                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;

                high--;
            }
        }
    }
}

/*
===============================================================================
Problem Statement
===============================================================================

Given an array containing only 0s, 1s, and 2s,
sort the array in-place without using any built-in sorting algorithm.

The sorting order should be:

0 → 1 → 2

Example:

Input:
[2,0,2,1,1,0]

Output:
[0,0,1,1,2,2]

===============================================================================
Intuition
===============================================================================

Since the array contains only three distinct values (0, 1, and 2),
there is no need to use a general sorting algorithm like Arrays.sort().

Instead, divide the array into four regions using three pointers.

Before low        -> All 0s

Between low & mid -> All 1s

Between mid & high -> Unprocessed elements

After high        -> All 2s

Process one element at a time until the entire array is sorted.

===============================================================================
Algorithm
===============================================================================

Step 1:
Initialize three pointers.

low = 0
mid = 0
high = n - 1

------------------------------------------------------------

Step 2:

If nums[mid] == 0

• Swap nums[mid] and nums[low]

• Increment both low and mid.

------------------------------------------------------------

Step 3:

If nums[mid] == 1

• It is already in the correct region.

• Move mid forward.

------------------------------------------------------------

Step 4:

If nums[mid] == 2

• Swap nums[mid] and nums[high].

• Decrement high.

• Do NOT increment mid because the new element
  swapped from the end has not been processed yet.

Continue until mid > high.

===============================================================================
Dry Run
===============================================================================

Input:

[2,0,2,1,1,0]

Initially

low = 0
mid = 0
high = 5

------------------------------------------------------------

2 found

Swap(mid, high)

[0,0,2,1,1,2]

high = 4

------------------------------------------------------------

0 found

Swap(low, mid)

[0,0,2,1,1,2]

low = 1
mid = 1

------------------------------------------------------------

0 found

Swap(low, mid)

[0,0,2,1,1,2]

low = 2
mid = 2

------------------------------------------------------------

2 found

Swap(mid, high)

[0,0,1,1,2,2]

high = 3

------------------------------------------------------------

1 found

mid++

------------------------------------------------------------

1 found

mid++

Loop ends.

Final Answer:

[0,0,1,1,2,2]

===============================================================================
Interview Follow-up
===============================================================================

Q1. Why don't we increment mid after swapping with high?

Answer:

Because the element swapped from the high position
has not been processed yet.

It could be 0, 1, or 2.

Therefore, we must check it again.

------------------------------------------------------------

Q2. Why do we increment both low and mid when we find 0?

Because after swapping,

• 0 reaches its correct position.

• The element at mid has already been processed.

So both pointers can move forward safely.

===============================================================================
Real-World Applications
===============================================================================

1. Data Classification

Used when data belongs to a fixed number of categories
and needs to be grouped efficiently.

Example:
High Priority, Medium Priority, Low Priority tasks.

------------------------------------------------------------

2. Network Packet Processing

Routers and network systems classify packets into
different priority levels (High, Medium, Low)
before forwarding them.

Grouping packets efficiently improves processing speed.

===============================================================================
Key Takeaways
===============================================================================

✔ Uses Three Pointers.

✔ Also known as the Dutch National Flag Algorithm.

✔ Performs sorting in a single traversal.

✔ No extra array is required.

✔ Time Complexity : O(n)

✔ Space Complexity : O(1)

✔ Interview Trick:

After swapping with the high pointer,
never increment mid immediately because
the swapped element still needs to be checked.

===============================================================================
*/

/*
===============================================================================
Pattern
===============================================================================

Pattern Name:
Dutch National Flag (Three Pointer)

Other Problems Using This Pattern:

• Move Zeroes
• Remove Duplicates from Sorted Array
• Remove Element
• Partition Array
• Quick Sort Partition
• Segregate Even & Odd Numbers

===============================================================================
*/
