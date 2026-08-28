/*
===============================================================================
Problem: Roman to Integer
Difficulty: Easy
Platform: LeetCode 13
Pattern: Hashing / Right-to-Left Traversal
===============================================================================

Problem Statement
-----------------

Given a Roman numeral string, convert it into an integer.

Roman numerals:

I = 1
V = 5
X = 10
L = 50
C = 100
D = 500
M = 1000


===============================================================================
Approach
===============================================================================

Traverse the string from RIGHT to LEFT.

Normally, Roman numeral values are added.

But when a smaller value appears before a larger value, it must be
SUBTRACTED.

Examples:

IV = 4

V - I
5 - 1 = 4


IX = 9

X - I
10 - 1 = 9


XL = 40

L - X
50 - 10 = 40


===============================================================================
Key Pattern
===============================================================================

While traversing from right to left:

        current < previous
                |
                ↓
            SUBTRACT


        current >= previous
                |
                ↓
              ADD


After processing:

previous = current


===============================================================================
Code
===============================================================================
*/

class Solution {

    public int romanToInt(String s) {

        int totalSum = 0;
        int previous = 0;

        for (int i = s.length() - 1; i >= 0; i--) {

            int current = 0;

            switch (s.charAt(i)) {

                case 'I':
                    current = 1;
                    break;

                case 'V':
                    current = 5;
                    break;

                case 'X':
                    current = 10;
                    break;

                case 'L':
                    current = 50;
                    break;

                case 'C':
                    current = 100;
                    break;

                case 'D':
                    current = 500;
                    break;

                case 'M':
                    current = 1000;
                    break;
            }

            if (current < previous) {
                totalSum -= current;
            } else {
                totalSum += current;
            }

            previous = current;
        }

        return totalSum;
    }
}


/*
===============================================================================
Dry Run
===============================================================================

Input:

"1994" → "MCMXCIV"


Traverse from RIGHT to LEFT:

Character       Current       Previous       Operation       Total
-------------------------------------------------------------------

V                  5              0             +5             5

I                  1              5             -1             4

C                100              1            +100           104

X                 10            100             -10            94

M               1000             10            +1000          1094

C                100            1000            -100          994

M               1000            100            +1000         1994


Answer:

1994


===============================================================================
Example: "III"
===============================================================================

Start from right:

I → +1
I → +1
I → +1

Total = 3


===============================================================================
Example: "IV"
===============================================================================

Start from right:

V = 5

previous = 0

5 >= 0

→ Add 5

Total = 5


Next:

I = 1

1 < 5

→ Subtract 1

Total = 4


Answer = 4


===============================================================================
Example: "VI"
===============================================================================

Start from right:

I = 1

→ Add 1


V = 5

5 > 1

→ Add 5


Answer = 6


===============================================================================
Why Traverse From Right To Left?
===============================================================================

The subtraction rule becomes very easy.

Example:

IX

When we reach I:

I = 1

Previous value = X = 10

Since:

1 < 10

we subtract I.


Therefore:

10 - 1 = 9


If we traverse from left to right, we need to look ahead.

Right-to-left traversal avoids that.


===============================================================================
Important Mistake to Avoid
===============================================================================

Do NOT simply add every Roman numeral.

Wrong:

IV

I + V

= 1 + 5

= 6


Correct:

I comes before a larger value V.

Therefore:

V - I

= 5 - 1

= 4


===============================================================================
Alternative Implementation
===============================================================================

Instead of switch, we can use a HashMap:

Map<Character, Integer> map = new HashMap<>();

map.put('I', 1);
map.put('V', 5);
map.put('X', 10);
map.put('L', 50);
map.put('C', 100);
map.put('D', 500);
map.put('M', 1000);


Then:

int current = map.get(s.charAt(i));


For this problem, switch is also perfectly fine because there are
only seven fixed Roman numeral characters.


===============================================================================
Complexity
===============================================================================

Time Complexity:

O(n)

We visit every character exactly once.


Space Complexity:

O(1)

Only a few integer variables are used.


===============================================================================
Interview Takeaway
===============================================================================

When you see Roman to Integer:

Think:

RIGHT → LEFT


Maintain:

previous


For every character:

if current < previous

    subtract


else

    add


Then:

previous = current


Pattern:

Right-to-left traversal + comparison with previous value.


===============================================================================
Similar Problems / Patterns
===============================================================================

• Integer to Roman
• String parsing
• Character-to-value mapping
• HashMap lookup
• Greedy-style traversal
• Two-direction string traversal


===============================================================================
*/ 
