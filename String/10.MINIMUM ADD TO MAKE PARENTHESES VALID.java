/*
================================================================================
                    MINIMUM ADD TO MAKE PARENTHESES VALID
                              LEETCODE 921
================================================================================

Problem:
Given a string s of '(' and ')', return the minimum number of parentheses
that must be added to make the resulting parentheses string valid.

Example:
    s = "())"  -> 1
    s = "((("  -> 3

================================================================================
                              SOLUTION
================================================================================
*/

import java.util.Stack;

class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        int ans = 0;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ')' && !st.isEmpty()) {
                st.pop();
            }
            else if(s.charAt(i) == ')' && st.isEmpty()) {
                ans++;
            }
            else {
                st.push(s.charAt(i));
            }
        }

        ans += st.size();
        return ans;
    }
}

/*
================================================================================
                              DETAILED EXPLANATION
================================================================================

The idea is to keep track of unmatched opening parentheses '('.

When we see '(' -> push it into the stack.

When we see ')':

1. Stack is not empty:
   There is an unmatched '(' available, so pair them and pop.

2. Stack is empty:
   There is no '(' available for this ')'. Therefore this ')' needs an
   opening '(' to be added before it, so ans++.

At the end, any '(' remaining in the stack also needs a matching ')'.
Therefore:

    answer = unmatched ')' + remaining '('

================================================================================
                              DRY RUN
================================================================================

Input: "())"

'(' -> push        stack = ['('], ans = 0
')' -> pop         stack = [],    ans = 0
')' -> no match    stack = [],    ans = 1

At the end:
    ans += stack.size()
    ans = 1

Answer = 1

================================================================================
                         IMPORTANT PATTERN
================================================================================

    '(' -> push
    ')' -> if '(' available, pop
           otherwise ans++

At the end:

    ans + stack.size()

================================================================================
                              COMPLEXITY
================================================================================

Time:  O(N)
Space: O(N) in the worst case.

================================================================================
                         COMMON MISTAKES
================================================================================

1. Forgetting unmatched ')'.
   Example: ") ))" conceptually requires one opening '(' for each unmatched ')'.

2. Forgetting remaining '('.
   Example: "(((" requires three ')' characters.

3. Checking only the total number of '(' and ')'.
   Example: ")(" has equal counts but is still invalid; answer = 2.

================================================================================
                         INTERVIEW TAKEAWAY
================================================================================

Match parentheses whenever possible, count unmatched closing parentheses
immediately, and count unmatched opening parentheses at the end.

================================================================================
                          SIMILAR PROBLEMS
================================================================================

- Valid Parentheses
- Remove Outermost Parentheses
- Minimum Remove to Make Valid Parentheses
- Longest Valid Parentheses
- Valid Parenthesis String

================================================================================
*/
