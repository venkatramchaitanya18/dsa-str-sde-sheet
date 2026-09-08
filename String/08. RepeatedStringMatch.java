/*
================================================================================
PROBLEM: Repeated String Match
LEETCODE: 686
DIFFICULTY: Medium
TOPIC: Strings / Pattern Matching
================================================================================

PROBLEM STATEMENT
-----------------
Given strings a and b, return the minimum number of times a must be repeated
so that b is a substring of the repeated string.

If b can never become a substring, return -1.

Example:
a = "abcd", b = "cdabcdab"
Answer = 3

================================================================================
MY SOLUTION
================================================================================
*/

class Solution {
    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder(a);
        int count = 1;

        // Grow until the string is long enough to potentially contain b
        while (sb.length() < b.length()) {
            sb.append(a);
            count++;
        }

        // Check after reaching/exceeding b's length
        if (sb.toString().contains(b)) {
            return count;
        }

        // b may cross the boundary between two repetitions
        sb.append(a);

        if (sb.toString().contains(b)) {
            return count + 1;
        }

        return -1;
    }
}

/*
================================================================================
APPROACH
================================================================================

1. Start with one copy of a.
2. Keep appending the ORIGINAL string a until the constructed string has
   length >= b.length().
3. Check whether the constructed string contains b.
4. If not, append one more copy of a and check again.
5. If b is still not found, return -1.

WHY ONE EXTRA COPY?
-------------------
Even when the repeated string has the same length as b, b may not align with
the beginning of a repetition.

Example:
a = "abcd"
b = "cdabcdab"

After 2 repetitions:
"abcdabcd"

b is not present.

After one more repetition:
"abcdabcdabcd"

"cdabcdab" appears starting at index 2.

Therefore, one extra repetition handles the boundary/shift case.

================================================================================
DRY RUN
================================================================================

a = "abcd"
b = "cdabcdab"

Initially:
sb = "abcd"
count = 1

Length of sb = 4
Length of b  = 8

4 < 8
append "abcd"

sb = "abcdabcd"
count = 2

Now 8 < 8 is false.

Check:
"abcdabcd".contains("cdabcdab")
false

Append one more "abcd":

sb = "abcdabcdabcd"

Check:
"abcdabcdabcd".contains("cdabcdab")
true

Return:
count + 1 = 3

================================================================================
WHY THE EARLIER VERSION GOT TIME LIMIT EXCEEDED
================================================================================

Earlier the loop used:

while (i < b.length())

and performed contains() on every iteration.

For very large strings, repeatedly converting StringBuilder to String and
searching with contains() is expensive.

The improved version grows only until sb.length() >= b.length(), then performs
at most two substring checks.

================================================================================
IMPORTANT JAVA CONCEPT
================================================================================

StringBuilder sb = new StringBuilder(a);

StringBuilder is mutable, so:

sb.append(a);

changes sb.

Always append the ORIGINAL a.

Do NOT append the already-growing sb to itself, because that doubles the
string:

abcd
abcdabcd
abcdabcdabcdabcd
...

================================================================================
IS THIS RABIN-KARP?
================================================================================

NO.

The solution above is NOT implementing Rabin-Karp.

It uses:

StringBuilder + String.contains()

The problem is related to string matching, so Rabin-Karp can be used for the
substring-search part, but this solution does not implement Rabin-Karp.

================================================================================
WHAT IS RABIN-KARP?
================================================================================

Rabin-Karp is a string pattern-matching algorithm.

Instead of comparing the complete pattern with every possible substring,
it calculates a HASH VALUE for the pattern and for each window of the text.

If hash values are different:
    The strings definitely do not match.

If hash values are equal:
    Compare the actual characters because a hash collision is possible.

Example:

Text:
"abcdef"

Pattern:
"cde"

Pattern length = 3.

Check windows:

"abc"
"bcd"
"cde"
"def"

When the hash of a window equals the pattern hash, verify the characters.

"cde" == "cde"

Pattern found.

================================================================================
WHY IS IT CALLED RABIN-KARP?
================================================================================

The algorithm was developed by Michael O. Rabin and Richard M. Karp.

Therefore:

Rabin + Karp = Rabin-Karp

================================================================================
ROLLING HASH
================================================================================

The most important idea in Rabin-Karp is the ROLLING HASH.

Suppose:

Text = "abcdef"
Pattern length = 3

First window:

"abc"

Move one position:

"bcd"

Instead of calculating the new hash completely from scratch, the algorithm
updates the previous hash:

Old window:
[a b c]

New window:
[b c d]

Conceptually:

Remove the contribution of 'a'
Add the contribution of 'd'

This makes moving the window efficient.

================================================================================
RABIN-KARP VS YOUR SOLUTION
================================================================================

YOUR SOLUTION
-------------
Build repeated string
        |
        v
String.contains(b)

RABIN-KARP
----------
Build/search text
        |
        v
Calculate pattern hash
        |
        v
Calculate rolling window hashes
        |
        v
Compare hashes
        |
        v
Verify characters when hashes match

================================================================================
COMPLEXITY
================================================================================

Let:

n = length of a
m = length of b

Your solution:
- Building the repeated string: approximately O(m)
- contains() performs substring searching internally.
- Exact practical complexity depends on the Java String implementation and
  input.

Space:
- O(m + n) approximately for the constructed string.

IMPORTANT:
This is a practical Java solution, NOT a manual Rabin-Karp implementation.

================================================================================
INTERVIEW TAKEAWAY
================================================================================

1. Repeated String Match is fundamentally a substring matching problem.
2. Repeat a until it is long enough to contain b.
3. Check one additional repetition because b can cross a repetition boundary.
4. StringBuilder is useful because it is mutable.
5. Do not append the already-growing StringBuilder to itself.
6. This solution uses Java's contains(), not Rabin-Karp.
7. Rabin-Karp is a separate pattern-matching algorithm based on hashing.
8. The key Rabin-Karp concept is the rolling hash.

================================================================================
SIMILAR STRING PROBLEMS
================================================================================

- LeetCode 28  - Find the Index of the First Occurrence in a String
- LeetCode 459 - Repeated Substring Pattern
- LeetCode 796 - Rotate String
- LeetCode 187 - Repeated DNA Sequences
- LeetCode 214 - Shortest Palindrome

================================================================================
BACKEND JAVA CONNECTION
================================================================================

String matching appears in backend development in:

- Searching text in API responses
- Validating URL/path patterns
- Log searching
- Filtering request data
- Checking prefixes/suffixes
- Searching user-entered text

Java provides:

contains()
startsWith()
endsWith()
indexOf()
substring()

Understanding pattern matching helps when optimizing text-heavy backend code.

================================================================================
PATTERN TO REMEMBER
================================================================================

"Build enough -> check -> add one extra -> check -> otherwise -1"

================================================================================
*/
