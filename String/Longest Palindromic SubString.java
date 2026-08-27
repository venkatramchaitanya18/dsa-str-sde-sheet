/*
===============================================================================
Problem: Longest Palindromic Substring
Difficulty: Medium
Platform: LeetCode 5
Pattern: Two Pointers / Expand Around Center
===============================================================================

Code
----

class Solution {

    public int expand(String s, int left, int right) {

        while (left >= 0 &&
               right < s.length() &&
               s.charAt(left) == s.charAt(right)) {

            left--;
            right++;
        }

        // left and right are now one position outside
        // the actual palindrome.
        return right - left - 1;
    }

    public String longestPalindrome(String s) {

        int start = 0;
        int maxLength = 1;

        for (int i = 0; i < s.length(); i++) {

            // Odd-length palindrome
            int odd = expand(s, i, i);

            // Even-length palindrome
            int even = expand(s, i, i + 1);

            int length = Math.max(odd, even);

            if (length > maxLength) {

                maxLength = length;

                // Find the starting index of the palindrome
                start = i - (length - 1) / 2;
            }
        }

        return s.substring(start, start + maxLength);
    }
}


===============================================================================
Problem Statement
===============================================================================

Given a string s, return the longest palindromic substring.

A palindrome is a string that reads the same forward and backward.

Example:

Input:
"babad"

Output:
"bab"

"aba" is also a valid answer.

===============================================================================
Approach
===============================================================================

Use the "Expand Around Center" technique.

Every palindrome has a center.

There are two possibilities:

1. Odd-length palindrome

Example:

"aba"

    a
    ↑
  center

Start with:

left = i
right = i


2. Even-length palindrome

Example:

"abba"

     b b
     ↑ ↑
   center

Start with:

left = i
right = i + 1


For every index, check both possibilities.

Expand left and right while:

s.charAt(left) == s.charAt(right)

Keep track of the longest palindrome found.

===============================================================================
How expand() Works
===============================================================================

Example:

s = "babad"

Start:

left = 1
right = 1

        a
        ↑
       center


Expand:

left = 0
right = 2

        b a b
        ↑   ↑

Characters are equal.

Expand again:

left = -1
right = 3

Now the loop stops because left < 0.


Important:

left and right are now OUTSIDE the palindrome.

Actual palindrome:

left + 1  --->  right - 1


Length:

(right - 1) - (left + 1) + 1

= right - left - 1

Therefore:

return right - left - 1;


===============================================================================
Why Check Both Odd and Even?
===============================================================================

Odd palindrome:

"aba"

      a
      ↑
   center


Call:

expand(s, i, i)


Even palindrome:

"abba"

    b b
    ↑ ↑
  center


Call:

expand(s, i, i + 1)


Both are necessary.

Otherwise, we would miss even-length palindromes such as:

"bb"

"abba"

"noon"


===============================================================================
Understanding start Formula
===============================================================================

We use:

start = i - (length - 1) / 2;


Think:

i = center of palindrome

(length - 1) / 2 = number of positions to move
                   from the center to the start


Example:

"bab"

Indexes:

0 1 2
b a b
  ↑
  i = 1


length = 3

(length - 1) / 2

= (3 - 1) / 2

= 1


start = i - 1

      = 1 - 1

      = 0


So:

start = 0


For:

"abcba"

Indexes:

0 1 2 3 4
a b c b a
    ↑
    i = 2


length = 5

(5 - 1) / 2

= 2


start = 2 - 2

      = 0


===============================================================================
Important Mistakes I Made
===============================================================================

Mistake 1:
----------

Wrong:

int even = expand(s, i - 1, i + 1);


Correct:

int even = expand(s, i, i + 1);


Reason:

For an even palindrome, the center is between two characters.

Example:

"bb"

  b b
  ↑ ↑
  i i+1


Mistake 2:
----------

Wrong:

if(length > maxLength){
    maxLength = length;
}

start = i - (length - 1) / 2;


Problem:

start gets updated even when the palindrome isn't the longest.


Correct:

if(length > maxLength){

    maxLength = length;

    start = i - (length - 1) / 2;
}


Mistake 3:
----------

Initially it may seem that:

return right - left + 1;


is correct.

But after the expansion loop, left and right have already moved
outside the palindrome.

Therefore:

return right - left - 1;


===============================================================================
Dry Run
===============================================================================

Input:

"babad"


i = 0

Odd:

"b"

length = 1


i = 1

Odd:

"bab"

length = 3


maxLength = 3

start = 0


i = 2

Odd:

"aba"

length = 3

Not greater than maxLength.


Final:

start = 0
maxLength = 3


substring:

s.substring(0, 3)

= "bab"


===============================================================================
Why This Approach?
===============================================================================

We could use Dynamic Programming, but Expand Around Center is simpler
and uses constant extra space.

The key observation is:

Every palindrome has a center.

So instead of checking every possible substring,

we expand around every possible center.


===============================================================================
Pattern Learned
===============================================================================

String

+

Two Pointers

+

Expand Around Center


This pattern is useful when a problem asks about:

• Palindromes

• Symmetric substrings

• Longest palindromic substring

• Counting palindromic substrings


===============================================================================
Alternative Approach
===============================================================================

Dynamic Programming can also solve this problem.

DP idea:

dp[i][j] = whether s.substring(i, j + 1) is a palindrome.


But it requires:

O(n²) space.

Expand Around Center is better here because it requires:

O(1) extra space.


===============================================================================
Complexity
===============================================================================

There are n possible centers.

For each center, we may expand up to O(n).

Time Complexity:

O(n²)


Extra Space:

O(1)


===============================================================================
Interview Takeaway
===============================================================================

When you see:

"Longest Palindromic Substring"

Immediately think:

Expand Around Center


For every index:

1. Check odd palindrome

   expand(i, i)


2. Check even palindrome

   expand(i, i + 1)


3. Keep the longest one.


===============================================================================
Similar Problems
===============================================================================

• Valid Palindrome

• Palindromic Substrings

• Longest Palindromic Subsequence

• Palindrome Partitioning

• Palindrome Partitioning II


===============================================================================
Backend Java Connection
===============================================================================

Palindrome problems themselves are not commonly used directly in
backend development.

However, the underlying concepts are useful:

• Two-pointer techniques
• String processing
• Efficient substring searching
• Character comparison
• Algorithm optimization

String processing and efficient algorithms are useful when handling
large text data in backend applications.


===============================================================================
*/
