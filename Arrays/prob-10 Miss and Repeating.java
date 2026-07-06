/*
 * ============================================================================
 * Problem Name  : Find Missing and Repeating Number
 *
 * Platform      : GeeksforGeeks
 *
 * Difficulty    : Medium
 *
 * Topic         : Arrays, Mathematics
 *
 * Time Complexity  : O(n)
 * Space Complexity : O(1)
 * ============================================================================
 */

class Solution {

    ArrayList<Integer> findTwoElement(int arr[]) {

        int n = arr.length;

        // Stores the actual sum of array elements.
        long sum = 0;

        // Expected sum of numbers from 1 to n.
        long expectedSum = (long) n * (n + 1) / 2;

        // Stores the actual sum of squares.
        long sumSq = 0;

        // Expected sum of squares from 1² to n².
        long expectedSumSq = (long) n * (n + 1) * (2L * n + 1) / 6;

        // Calculate actual sum and actual sum of squares.
        for (int num : arr) {

            sum += num;
            sumSq += (long) num * num;
        }

        // (Missing - Repeating)
        long missMinusRep = expectedSum - sum;

        // (Missing² - Repeating²)
        long missMinusRepSq = expectedSumSq - sumSq;

        // (Missing + Repeating)
        // Using:
        // a² - b² = (a-b)(a+b)
        long missPlusRep = missMinusRepSq / missMinusRep;

        // Missing Number
        long missing = (missMinusRep + missPlusRep) / 2;

        // Repeating Number
        long repeating = missPlusRep - missing;

        ArrayList<Integer> ans = new ArrayList<>();

        // GFG expects [Repeating, Missing]
        ans.add((int) repeating);
        ans.add((int) missing);

        return ans;
    }
}

/*
===============================================================================
Problem Statement
===============================================================================

An array contains numbers from 1 to n.

One number is missing.

One number appears twice.

Find both the repeating number
and the missing number.

Return

[Repeating, Missing]

Example

Input

[1,3,3]

Output

[3,2]

===============================================================================
Intuition
===============================================================================

Instead of checking every element,

use Mathematics.

We know

Expected Sum

and

Expected Sum of Squares

for numbers from 1 to n.

Comparing them with the actual values
gives us two equations.

Solving those equations gives
both the missing and repeating numbers.

===============================================================================
Mathematical Derivation
===============================================================================

Let

Missing = M

Repeating = R

------------------------------------------------------------

Expected Sum

1 + 2 + ... + n

------------------------------------------------------------

Actual Sum

Array Sum

------------------------------------------------------------

Equation 1

Expected Sum - Actual Sum

=

M - R

Let

X = M - R

------------------------------------------------------------

Expected Square Sum

1² + 2² + ... + n²

------------------------------------------------------------

Actual Square Sum

Array Square Sum

------------------------------------------------------------

Equation 2

ExpectedSquareSum - ActualSquareSum

=

M² - R²

Using

a² - b²

=

(a-b)(a+b)

Therefore

M² - R²

=

(M-R)(M+R)

Since

M-R = X

Then

M+R

=

(M²-R²)/(M-R)

Let

Y = M + R

------------------------------------------------------------

Now we have

M - R = X

M + R = Y

Add both equations

2M = X + Y

Missing

=

(X + Y)/2

Repeating

=

Y - Missing

===============================================================================
Dry Run
===============================================================================

Input

[1,3,3]

------------------------------------------------------------

Expected Sum

1+2+3 = 6

Actual Sum

1+3+3 = 7

M-R

6-7

=

-1

------------------------------------------------------------

Expected Square Sum

1+4+9 = 14

Actual Square Sum

1+9+9 = 19

M²-R²

14-19

=

-5

------------------------------------------------------------

M+R

(-5)/(-1)

=

5

------------------------------------------------------------

Missing

(-1+5)/2

=

2

Repeating

5-2

=

3

Answer

[3,2]

===============================================================================
Important Note About Negative Signs
===============================================================================

Sometimes

Expected Sum - Actual Sum

becomes negative.

Example

Expected Sum = 6

Actual Sum = 7

Then

6 - 7

=

-1

This simply means

Missing < Repeating

There is nothing wrong.

The negative sign is automatically handled
while solving the equations.

Example

M - R = -1

M + R = 5

Missing

=

(-1 + 5)/2

=

2

Repeating

=

5 - 2

=

3

So don't worry if

(Missing - Repeating)

is negative.

===============================================================================
Mistakes I Made While Solving
===============================================================================

1.

Initially,

I returned

[Missing, Repeating]

But GFG expects

[Repeating, Missing]

------------------------------------------------------------

2.

I used int.

For large values of n,

sum of squares overflows.

Always use

long

for mathematical calculations.

------------------------------------------------------------

3.

I forgot that

a² - b²

=

(a-b)(a+b)

This identity is the key to solving
the problem in O(1) extra space.

===============================================================================
Interview Follow-up
===============================================================================

Q1.

Why use long instead of int?

Answer

The sum of squares can exceed
the range of int for large n.

------------------------------------------------------------

Q2.

Can this be solved without Mathematics?

Yes.

Using

• Hashing

• Frequency Array

• Visited Array

All require O(n) extra space.

------------------------------------------------------------

Q3.

Can this be solved by modifying the array?

Yes.

Using Negative Marking.

That solution also runs in O(n)
with O(1) extra space.

===============================================================================
Another O(1) Approach (Negative Marking)
===============================================================================

Idea

Use each value as an index.

For every element

value = abs(arr[i])

Visit

arr[value-1]

If it is positive,

make it negative.

If it is already negative,

that value is the repeating number.

After traversal,

the index whose value remains positive
is the missing number.

Example

Input

[3,1,3]

Step 1

Visit index 2

Make negative

[3,1,-3]

Step 2

Visit index 0

[-3,1,-3]

Step 3

Visit index 2 again

Already negative

Repeating = 3

Now check positive values

Index 1 is positive

Missing = 2

===============================================================================
Real-World Applications
===============================================================================

1. Database Validation

Used to detect duplicate IDs
and missing records in datasets.

------------------------------------------------------------

2. Inventory Management

Helps identify missing product IDs
and duplicate inventory entries.

===============================================================================
Pattern
===============================================================================

Pattern Name

Mathematical Equations

Other Problems

• Missing Number

• Set Mismatch

• Find the Duplicate Number

• First Missing Positive

===============================================================================
Key Takeaways
===============================================================================

✔ Use Expected Sum and Actual Sum.

✔ Use Expected Square Sum and Actual Square Sum.

✔ Apply

a²-b²=(a-b)(a+b)

✔ Solve two equations to find

Missing

and

Repeating.

✔ Always use long.

✔ Time Complexity : O(n)

✔ Space Complexity : O(1)

===============================================================================
*/
