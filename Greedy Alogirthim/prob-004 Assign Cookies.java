/*
===============================================================================
Problem: Assign Cookies
Difficulty: Easy
Platform: LeetCode 455
===============================================================================

Code
----

class Solution {
    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);

        int ans = 0;

        int i = g.length - 1;
        int j = s.length - 1;

        while (i >= 0 && j >= 0) {

            if (g[i] <= s[j]) {

                ans++;
                i--;
                j--;

            } else {

                i--;
            }
        }

        return ans;
    }
}

===============================================================================
Problem Statement
===============================================================================

There are n children and m cookies.

Each child has a greed factor g[i].

Each cookie has a size s[j].

A child is satisfied only if

Cookie Size >= Greed Factor

Each child can receive only one cookie.

Each cookie can be used only once.

Return the maximum number of satisfied children.

===============================================================================
Optimal Approach (Greedy)
===============================================================================

Idea

1. Sort greed array.

2. Sort cookie array.

3. Start from the largest greed and largest cookie.

4. If the largest cookie satisfies the greediest child,
   assign it.

5. Otherwise, the greediest child cannot be satisfied,
   so skip that child.

===============================================================================
Algorithm
===============================================================================

Step 1

Sort greed array.

Step 2

Sort cookie array.

Step 3

Keep two pointers.

i -> Largest Greed

j -> Largest Cookie

Step 4

If

cookie >= greed

assign cookie

answer++

Move both pointers.

Otherwise

Current largest cookie cannot satisfy this child.

Move only greed pointer.

===============================================================================
Dry Run
===============================================================================

Greed

[1,2,3]

Cookies

[1,1]

After Sorting

Greed

1 2 3

Cookies

1 1

------------------------

Child = 3

Cookie = 1

1 < 3

Cannot satisfy.

Skip child.

------------------------

Child = 2

Cookie = 1

Cannot satisfy.

Skip child.

------------------------

Child = 1

Cookie = 1

Satisfied.

Answer = 1

===============================================================================
Example
===============================================================================

Greed

1 2

Cookies

1 2 3

Largest Child =2

Largest Cookie=3

Assign

Remaining

Child=1

Cookie=2

Assign

Answer

2

===============================================================================
Why Greedy Works?
===============================================================================

Always use the largest cookie for the greediest child.

Reason

If the largest cookie cannot satisfy the greediest child,

then no smaller cookie can satisfy that child.

So there is no point trying.

If it can satisfy,

give it immediately.

This never reduces future possibilities.

===============================================================================
Common Mistakes I Made
===============================================================================

✓ Forgot to sort arrays.

✓ Tried checking every cookie for every child.

Time Complexity became O(n²).

✓ Didn't realize starting from the end is simpler.

Largest cookie

↓

Largest greed

makes the greedy choice obvious.

===============================================================================
Pattern Learned
===============================================================================

Greedy

+

Sorting

+

Two Pointers

===============================================================================
Similar Problems
===============================================================================

✓ Fractional Knapsack

✓ Job Sequencing

✓ N Meetings in One Room

✓ Activity Selection

✓ Boats to Save People

✓ Maximum Units on a Truck

===============================================================================
Backend Java Applications
===============================================================================

Similar matching strategy is used in

• Resource Allocation

• Memory Allocation

• CPU Scheduling

• Task Assignment

• Server Resource Distribution

• Job Scheduling Systems

===============================================================================
Complexity
===============================================================================

Sorting

O(n log n + m log m)

Two Pointer Traversal

O(n + m)

Overall

O(n log n + m log m)

Space

O(1)

===============================================================================
Interview Takeaway
===============================================================================

Whenever you see

Sort

+

Pair two arrays

+

Need maximum assignments

think

Greedy + Two Pointers.

===============================================================================
