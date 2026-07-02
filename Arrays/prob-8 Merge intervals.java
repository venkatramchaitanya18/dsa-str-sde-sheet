/*
 * ============================================================================
 * Problem Name  : Merge Intervals
 *
 * Platform      : LeetCode 56
 *
 * Difficulty    : Medium
 *
 * Topic         : Arrays, Sorting, Greedy
 *
 * Time Complexity  : O(n log n)
 * Space Complexity : O(n)
 * ============================================================================
 */

class Solution {

    public int[][] merge(int[][] intervals) {

        // Step 1: Sort the intervals based on the starting point.
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Stores the merged intervals.
        List<List<Integer>> ans = new ArrayList<>();

        // Traverse all intervals.
        for (int i = 0; i < intervals.length; i++) {

            // If the answer list is empty
            // OR
            // the current interval starts after the previous interval ends,
            // there is no overlap.
            if (ans.isEmpty() || intervals[i][0] > ans.get(ans.size() - 1).get(1)) {

                ans.add(Arrays.asList(intervals[i][0], intervals[i][1]));
            }

            // Overlapping intervals.
            // Update the ending point of the previous interval.
            else {

                ans.get(ans.size() - 1).set(
                        1,
                        Math.max(ans.get(ans.size() - 1).get(1), intervals[i][1]));
            }
        }

        // Convert List<List<Integer>> to int[][]
        int[][] result = new int[ans.size()][2];

        for (int i = 0; i < ans.size(); i++) {

            result[i][0] = ans.get(i).get(0);
            result[i][1] = ans.get(i).get(1);
        }

        return result;
    }
}

/*
===============================================================================
Problem Statement
===============================================================================

Given an array of intervals where

intervals[i] = [start, end]

merge all overlapping intervals
and return the merged intervals.

Example

Input

[[1,3],[2,6],[8,10],[15,18]]

Output

[[1,6],[8,10],[15,18]]

===============================================================================
Intuition
===============================================================================

If intervals are not sorted,

we cannot determine whether two intervals overlap.

So,

first sort all intervals based on their starting values.

Then compare

Current Start

with

Previous End

If they overlap,

merge them.

Otherwise,

store them as a new interval.

===============================================================================
Algorithm
===============================================================================

Step 1

Sort all intervals by their starting point.

------------------------------------------------------------

Step 2

Create an empty answer list.

------------------------------------------------------------

Step 3

Traverse every interval.

If

Current Start > Previous End

No overlap.

Add the interval.

Otherwise,

Merge by updating

Previous End

=

max(Previous End, Current End)

------------------------------------------------------------

Step 4

Convert the List into int[][]

===============================================================================
Dry Run
===============================================================================

Input

[[1,3],[2,6],[8,10],[15,18]]

------------------------------------------------------------

Sorted

[[1,3],[2,6],[8,10],[15,18]]

------------------------------------------------------------

Answer

[]

------------------------------------------------------------

Take

[1,3]

Answer

[[1,3]]

------------------------------------------------------------

Take

[2,6]

2 <= 3

Overlap

Merge

[[1,6]]

------------------------------------------------------------

Take

[8,10]

8 > 6

No Overlap

[[1,6],[8,10]]

------------------------------------------------------------

Take

[15,18]

15 > 10

No Overlap

[[1,6],[8,10],[15,18]]

===============================================================================
Mistakes I Made While Solving
===============================================================================

1.

I compared

Current End

with

Previous Interval

instead of comparing

Current Start

with

Previous End.

Correct comparison:

intervals[i][0] > previousEnd

------------------------------------------------------------

2.

I wrote

ans.get(ans.size()-1)

thinking it would return the end value.

Actually,

it returns the complete List<Integer>.

Correct:

ans.get(ans.size()-1).get(1)

------------------------------------------------------------

3.

I used

add()

instead of

set().

Wrong

[1,6]

↓

add(8)

↓

[1,6,8]

Correct

set(1,8)

↓

[1,8]

------------------------------------------------------------

4.

I used

intervals[i][0]

inside Math.max().

Correct is

intervals[i][1]

because we update the ending point.

------------------------------------------------------------

5.

I tried

ans.get(i,0)

Java List doesn't support

get(i,j)

Correct

ans.get(i).get(0)

------------------------------------------------------------

6.

I used

math.max()

Correct

Math.max()

(Java is case-sensitive.)

------------------------------------------------------------

7.

I named a variable

final

But

final

is a reserved keyword in Java.

Use

result

instead.

===============================================================================
Interview Follow-up
===============================================================================

Q1.

Why do we sort first?

Answer

Sorting ensures that overlapping intervals
appear next to each other.

------------------------------------------------------------

Q2.

Why compare

Current Start

with

Previous End?

Because overlapping depends only on

Current Start <= Previous End

------------------------------------------------------------

Q3.

Can this problem be solved without sorting?

No.

Without sorting,

checking overlaps efficiently becomes difficult.

===============================================================================
Real-World Applications
===============================================================================

1. Calendar Scheduling

Google Calendar and Outlook merge overlapping meetings
to display continuous busy time slots.

------------------------------------------------------------

2. Database Query Optimization

Databases merge overlapping ranges
such as booking dates,
employee shifts,
and reservation periods
to reduce redundant records.

===============================================================================
Pattern
===============================================================================

Pattern Name

Sorting + Greedy (Interval Merging)

Other Problems

• Insert Interval

• Non-overlapping Intervals

• Meeting Rooms

• Meeting Rooms II

• Employee Free Time

• Minimum Number of Arrows to Burst Balloons

===============================================================================
Key Takeaways
===============================================================================

✔ Always sort intervals first.

✔ Compare

Current Start

with

Previous End.

✔ If overlap

Update

End = max(previousEnd, currentEnd)

✔ If no overlap

Simply add the interval.

✔ Time Complexity

O(n log n)

✔ Space Complexity

O(n)

===============================================================================
*/
