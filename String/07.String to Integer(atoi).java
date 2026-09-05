/*
============================================================
Problem: String to Integer (atoi)
LeetCode: 8
Difficulty: Medium
Topic: Strings
============================================================

PROBLEM STATEMENT
-----------------
Implement myAtoi(String s) to convert a string into a
32-bit signed integer.

Rules:
1. Ignore leading whitespace.
2. Check an optional '+' or '-' sign.
3. Read consecutive digits.
4. Stop at the first non-digit.
5. If the number is outside the int range, return the
   appropriate limit.

Integer range:
Integer.MIN_VALUE = -2147483648
Integer.MAX_VALUE =  2147483647


============================================================
APPROACH
============================================================

String
  ↓
trim()
  ↓
Check sign
  ↓
Read digits
  ↓
Build number using long
  ↓
Check overflow
  ↓
Apply sign
  ↓
Return int


============================================================
CODE
============================================================
*/

class Solution {

    public int myAtoi(String s) {

        // Remove leading and trailing spaces
        s = s.trim();

        int i = 0;
        int sign = 1;

        // Use long so we can temporarily store values
        // larger than the int range.
        long result = 0;

        // Check sign
        if (i < s.length() &&
            (s.charAt(i) == '+' || s.charAt(i) == '-')) {

            if (s.charAt(i) == '-') {
                sign = -1;
            }

            i++;
        }

        // Read digits
        while (i < s.length() && Character.isDigit(s.charAt(i))) {

            int digit = s.charAt(i) - '0';

            // Build the number
            result = result * 10 + digit;

            // Positive overflow
            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            // Negative overflow
            if (sign == -1 && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            i++;
        }

        // Apply sign
        return (int) (result * sign);
    }
}


/*
============================================================
DETAILED EXPLANATION
============================================================

1. TRIM THE STRING
============================================================

s = s.trim();

Example:

"   -42abc   "

becomes:

"-42abc"

This makes leading-space handling simple.


============================================================
2. CHECK THE SIGN
============================================================

Initially:

sign = 1

If we find '-':
sign = -1

If we find '+':
sign stays 1.


============================================================
3. CONVERT CHARACTER TO DIGIT
============================================================

int digit = s.charAt(i) - '0';

Example:

'5' - '0' = 5

This works because character digits have consecutive
Unicode values.


============================================================
4. BUILD THE NUMBER
============================================================

result = result * 10 + digit;

Example for "123":

result = 0

Read 1:
0 * 10 + 1 = 1

Read 2:
1 * 10 + 2 = 12

Read 3:
12 * 10 + 3 = 123


============================================================
5. WHY USE LONG?
============================================================

An int can store only:

-2147483648 to 2147483647

But input may contain a larger number.

Example:

"2147483648"

This cannot fit inside int.

So we build the number using:

long result = 0;

Then check whether it has gone outside the int range.


============================================================
6. POSITIVE OVERFLOW
============================================================

if (sign == 1 && result > Integer.MAX_VALUE) {
    return Integer.MAX_VALUE;
}

Example:

result = 2147483648

Integer.MAX_VALUE = 2147483647

Therefore, return:

2147483647


============================================================
7. NEGATIVE OVERFLOW
============================================================

if (sign == -1 && -result < Integer.MIN_VALUE) {
    return Integer.MIN_VALUE;
}

Example:

"-999999999999"

If the negative value becomes smaller than:

-2147483648

return:

Integer.MIN_VALUE


============================================================
8. STOP AT NON-DIGIT
============================================================

Example:

"123abc"

Process:

1 → 2 → 3

When 'a' is reached:

Character.isDigit('a') == false

So the loop stops.

Answer = 123


============================================================
DRY RUN
============================================================

Input:

"   -42abc"

After trim:

"-42abc"

Sign:

'-' → sign = -1

Read '4':

result = 0 * 10 + 4
       = 4

Read '2':

result = 4 * 10 + 2
       = 42

Read 'a':

Not a digit → stop

Final:

42 * -1 = -42


============================================================
COMMON MISTAKES
============================================================

1. Using int while building a very large number.
   Use long for the easier approach.

2. Forgetting '+' and '-'.

3. Continuing after a non-digit.

4. Using Integer.parseInt(), which skips the main
   conversion logic this problem is testing.


============================================================
ALTERNATIVE INTERVIEW APPROACH
============================================================

Instead of long, overflow can be detected BEFORE:

result = result * 10 + digit;

using:

result > (Integer.MAX_VALUE - digit) / 10

This is more confusing initially, but is useful to know
for interviews because it detects overflow before the
multiplication.


============================================================
COMPLEXITY
============================================================

Time: O(n)

Space: O(1)


============================================================
PATTERN
============================================================

String Traversal
+
Parsing
+
Edge Case Handling

Remember:

trim
 ↓
sign
 ↓
digits
 ↓
result = result * 10 + digit
 ↓
overflow check
 ↓
apply sign


============================================================
SIMILAR PROBLEMS
============================================================

1. Roman to Integer
2. Integer to Roman
3. Valid Number
4. Basic Calculator
5. Basic Calculator II


============================================================
BACKEND JAVA CONNECTION
============================================================

String parsing is common in backend development:

- Query parameters
- HTTP request parameters
- CSV data
- Configuration values
- User input validation
- Converting String values to integers

Example:

"10" → 10


============================================================
FINAL TAKEAWAY
============================================================

For learning, the long-based approach is easier:

Build number using long
        ↓
Check against Integer limits
        ↓
Return the limit if overflow occurs

Once comfortable, learn the pre-overflow formula too.
============================================================
*/

