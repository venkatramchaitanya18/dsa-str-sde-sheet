/*
===============================================================================
Problem: Remove Outermost Parentheses
Difficulty: Easy
Platform: LeetCode 1021
Pattern: Balance Count / String Processing
===============================================================================


===============================================================================
APPROACH 1: Using substring() and start index
===============================================================================

Code
----

class Solution {

    public String removeOuterParentheses(String s) {

        int count = 0;
        int start = 0;

        StringBuilder ans = new StringBuilder("");

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                count++;
            }
            else if (s.charAt(i) == ')') {
                count--;
            }

            // Primitive parentheses group is completed
            if (count == 0) {

                // Remove the first and last outer parentheses
                ans.append(s.substring(start + 1, i));

                // Start of next primitive group
                start = i + 1;
            }
        }

        return ans.toString();
    }
}


===============================================================================
APPROACH 1 - Explanation
===============================================================================

We maintain:

    count
        → tracks the nesting level.

    start
        → stores the starting index of the current primitive group.

When count becomes 0, one primitive parentheses group is completed.

Example:

    s = "(()())(())"


Primitive groups:

    (()())
    (())


For:

    (()())

start = 0
i = 5

The outer parentheses are:

    index 0 → '('
    index 5 → ')'


Therefore:

    substring(start + 1, i)

becomes:

    substring(1, 5)

which gives:

    "()()"


For the second group:

    (())

start = 6
i = 9

Therefore:

    substring(7, 9)

gives:

    "()"


Final answer:

    "()()()"


===============================================================================
APPROACH 2: Balance Count Without substring()
===============================================================================

A cleaner approach is to decide whether each parenthesis is outermost
or inner while scanning the string.

Code
----

class Solution {

    public String removeOuterParentheses(String s) {

        int count = 0;

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                // If count > 0, this is an inner '('
                if (count > 0) {
                    ans.append('(');
                }

                count++;
            }
            else {

                count--;

                // If count > 0, this is an inner ')'
                if (count > 0) {
                    ans.append(')');
                }
            }
        }

        return ans.toString();
    }
}


===============================================================================
APPROACH 2 - Explanation
===============================================================================

The variable count represents the current nesting depth.

For an opening '(':

    If count == 0

        It is the outermost '('.

        Don't add it.

    If count > 0

        It is an inner '('.

        Add it.

Then increase count.


For a closing ')':

    First decrease count.

    If count == 0

        It is the outermost ')'.

        Don't add it.

    If count > 0

        It is an inner ')'.

        Add it.


===============================================================================
Example
===============================================================================

Input:

    "(()())"


Step by step:

Character: '('

count = 0

Outermost '('

Don't add.

count becomes 1.


Character: '('

count = 1

Inner '('

Add '('.

count becomes 2.


Character: ')'

count becomes 1.

Inner ')'

Add ')'.


Character: '('

count = 1

Inner '('

Add '('.

count becomes 2.


Character: ')'

count becomes 1.

Inner ')'

Add ')'.


Character: ')'

count becomes 0.

Outermost ')'

Don't add.


Result:

    "()()"


===============================================================================
Problem Statement
===============================================================================

Given a valid parentheses string s, remove the outermost parentheses of
every primitive parentheses string.

Example:

Input:

    "(()())(())"

Output:

    "()()()"


===============================================================================
Why count Works
===============================================================================

The important idea is the balance/nesting level.

Opening parenthesis:

    count++

Closing parenthesis:

    count--


A primitive group starts when:

    count: 0 → 1

and ends when:

    count: 1 → 0


Therefore, the parentheses responsible for:

    0 → 1

and:

    1 → 0

are the outermost parentheses.

We remove exactly those parentheses.


===============================================================================
Comparison of Both Approaches
===============================================================================

Approach 1:

    substring() + start index

Advantages:

• Easy to understand
• Finds each primitive group explicitly
• Good for learning the structure of the problem


Approach 2:

    Balance count + character-by-character processing

Advantages:

• No start variable
• No substring()
• Processes characters directly
• Cleaner logic
• Uses StringBuilder directly


For interviews, Approach 2 is generally cleaner.


===============================================================================
Important Mistakes
===============================================================================

Mistake 1:
----------

Initially tried:

    sb.deleteCharAt(start);
    sb.deleteCharAt(i);


Problem:

After deleting a character, indexes of StringBuilder change.

But i is based on the original string.

This can cause incorrect indexes.


Mistake 2:
----------

Using:

    String ans = "";

and repeatedly doing:

    ans += s.substring(...);


String is immutable in Java.

StringBuilder is better for repeatedly constructing a string.


Mistake 3:
----------

Forgetting that substring() has an exclusive ending index.

For:

    s.substring(start + 1, i)

characters from:

    start + 1

up to:

    i - 1

are included.

The character at index i is excluded.

This is exactly what we need because i is the position of the
outermost closing parenthesis.


===============================================================================
Pattern Learned
===============================================================================

Balance Counter

        +

String Processing

        +

StringBuilder


Whenever you see nested parentheses or brackets, think about:

    Balance / Depth / Count


===============================================================================
Complexity
===============================================================================

Approach 1:

Time Complexity:

    O(n)

Space Complexity:

    O(n)


Approach 2:

Time Complexity:

    O(n)

Space Complexity:

    O(n)

The O(n) space is required for the resulting answer.


===============================================================================
Interview Takeaway
===============================================================================

For parentheses problems, track the nesting depth.

Remember:

Opening:

    count++

Closing:

    count--


For removing outer parentheses:

    0 → 1
        ↓
    outermost opening
        ↓
    don't add


    1 → 0
        ↓
    outermost closing
        ↓
    don't add


Everything inside that outer layer should be added.


===============================================================================
Similar Problems
===============================================================================

• Valid Parentheses
• Minimum Remove to Make Valid Parentheses
• Valid Parenthesis String
• Longest Valid Parentheses
• Generate Parentheses
• Score of Parentheses


===============================================================================
Backend Java Connection
===============================================================================

Parentheses problems are not commonly used directly in backend development.

However, the underlying concepts are useful for:

• String processing
• Parsing
• Nested structure handling
• Input validation
• Stack-based processing
• Expression parsing
• JSON/XML-like structured data


===============================================================================
*/
