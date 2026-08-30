/*
===============================================================================
Problem: Longest Common Prefix
Difficulty: Easy
Platform: LeetCode 14
Topic: Strings
Pattern: Prefix Matching
===============================================================================

Problem Statement
-----------------

Write a function to find the longest common prefix string amongst an array
of strings.

If there is no common prefix, return an empty string "".

Example:

Input:
["flower", "flow", "flight"]

Output:
"fl"


===============================================================================
APPROACH 1: SORTING
===============================================================================

Idea
----

Sort the strings lexicographically.

After sorting, the strings with the greatest difference will be at the
beginning and end.

Therefore, we only need to compare:

    First string
          +
    Last string

Example:

["flower", "flow", "flight"]

After sorting:

["flight", "flow", "flower"]

Compare:

"flight"
"flower"

f == f   ✓
l == l   ✓
i != o   ✗

Answer:

"fl"


Code
----
*/

class Solution {

    public String longestCommonPrefix(String[] strs) {

        Arrays.sort(strs);

        String str1 = strs[0];
        String str2 = strs[strs.length - 1];

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < str1.length() && i < str2.length(); i++) {

            if (str1.charAt(i) == str2.charAt(i)) {

                ans.append(str1.charAt(i));

            } else {

                break;
            }
        }

        return ans.toString();
    }
}


/*
===============================================================================
APPROACH 2: HORIZONTAL SCANNING USING startsWith()
===============================================================================

Idea
----

Take the first string as the initial prefix.

Then compare this prefix with every other string.

If the current string does NOT start with the prefix:

    Remove the last character from prefix.

Continue until the current string starts with prefix.

Example:

["flower", "flow", "flight"]


Step 1:

prefix = "flower"


Compare with:

"flow"

Does "flow" start with "flower"?

NO

Remove last character:

"flowe"


NO

Remove last character:

"flow"


YES


Now:

prefix = "flow"


Step 2:

Compare with:

"flight"

Does "flight" start with "flow"?

NO

Remove last character:

"flo"

NO

Remove last character:

"fl"

YES


Final answer:

"fl"


===============================================================================
Code - Horizontal Scanning
===============================================================================
*/

class Solution {

    public String longestCommonPrefix(String[] strs) {

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {

            while (!strs[i].startsWith(prefix)) {

                prefix = prefix.substring(0, prefix.length() - 1);

                if (prefix.isEmpty()) {
                    return prefix;
                }
            }
        }

        return prefix;
    }
}


/*
===============================================================================
APPROACH 3: VERTICAL SCANNING
===============================================================================

Idea
----

Take the first string as the initial prefix.

Compare characters at the same position across all strings.

Example:

["flower", "flow", "flight"]


Compare index 0:

flower
flow
flight

f == f == f

✓


Compare index 1:

l == l == l

✓


Compare index 2:

o == o

But:

o != i

Therefore stop.

Answer:

"fl"


This approach does not require sorting.


===============================================================================
Code - Vertical Scanning
===============================================================================
*/

class Solution {

    public String longestCommonPrefix(String[] strs) {

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {

            int j = 0;

            while (j < prefix.length()
                    && j < strs[i].length()
                    && prefix.charAt(j) == strs[i].charAt(j)) {

                j++;
            }

            prefix = prefix.substring(0, j);

            if (prefix.isEmpty()) {
                return "";
            }
        }

        return prefix;
    }
}


/*
===============================================================================
COMPARISON OF APPROACHES
===============================================================================

1. Sorting
-----------

Steps:

Sort the array
     ↓
Take first and last string
     ↓
Compare their characters


Time:

O(n log n × L)


Extra Space:

Depends on sorting implementation.


---------------------------------------------------------------

2. Horizontal Scanning
----------------------

Steps:

Take first string as prefix
     ↓
Compare with next string
     ↓
Use startsWith()
     ↓
If false, remove last character
     ↓
Continue


Time:

Depends on prefix comparisons and substring/startsWith operations.

In the worst case it can approach:

O(n × L²)


Extra Space:

O(L) can occur because String operations may create new strings.


---------------------------------------------------------------

3. Vertical Scanning
--------------------

Steps:

Take first string
     ↓
Compare characters at same index
     ↓
Stop at mismatch


Time:

O(n × L)


Extra Space:

O(1) auxiliary space
(excluding the returned substring).


===============================================================================
WHICH APPROACH IS BETTER?
===============================================================================

For interview purposes:

Recommended:

Vertical Scanning


Why?

• No sorting
• Simple logic
• Easy to explain
• Efficient
• Directly compares characters
• O(n × L) time


Horizontal scanning with startsWith() is also very readable and is a
good solution when using Java's String API is acceptable.


===============================================================================
IMPORTANT LEARNING: startsWith()
===============================================================================

Java provides:

str.startsWith(prefix)


It checks whether a String begins with another String.

Example:

"flower".startsWith("flow")

true


"flower".startsWith("flowe")

true


"flower".startsWith("flower")

true


"flower".startsWith("flx")

false


In our solution:

while (!strs[i].startsWith(prefix))


means:

"While the current string does NOT start with our prefix,
keep reducing the prefix."


===============================================================================
IMPORTANT LEARNING: substring()
===============================================================================

We use:

prefix.substring(0, prefix.length() - 1)


This removes the last character.

Example:

prefix = "flower"


prefix.length() = 6


substring(0, 5)

= "flowe"


Again:

substring(0, 4)

= "flow"


So we keep shrinking the prefix from the right side.


===============================================================================
DRY RUN - HORIZONTAL SCANNING
===============================================================================

Input:

["flower", "flow", "flight"]


Initial:

prefix = "flower"


---------------------------------------------------------------

Compare with "flow":

"flow".startsWith("flower")

false

prefix = "flowe"


---------------------------------------------------------------

"flow".startsWith("flowe")

false

prefix = "flow"


---------------------------------------------------------------

"flow".startsWith("flow")

true


prefix = "flow"


---------------------------------------------------------------

Compare with "flight":

"flight".startsWith("flow")

false

prefix = "flo"


---------------------------------------------------------------

"flight".startsWith("flo")

false

prefix = "fl"


---------------------------------------------------------------

"flight".startsWith("fl")

true


Final:

prefix = "fl"


===============================================================================
EDGE CASES
===============================================================================

1. Only one string

["flower"]

Answer:

"flower"


2. No common prefix

["dog", "racecar", "car"]

Answer:

""


3. One string is completely a prefix of another

["flower", "flow"]

Answer:

"flow"


4. Empty string

[""]

Answer:

""


5. Different lengths

["abc", "ab", "abcd"]

Answer:

"ab"


===============================================================================
COMMON MISTAKES
===============================================================================

Mistake 1:
----------

Comparing only the first two strings.

Wrong idea:

Compare strs[0] with strs[1]
and return the result.


Why wrong?

The prefix must be common to ALL strings.


Example:

["flower", "flow", "flight"]

First two give:

"flow"

But "flight" only has:

"fl"


Correct answer:

"fl"


---------------------------------------------------------------

Mistake 2:
----------

Not checking the shorter string's length.

For example:

["ab", "abc"]


We cannot access:

strs[0].charAt(2)


because "ab" ends at index 1.


Therefore always check:

j < prefix.length()
&&
j < strs[i].length()


---------------------------------------------------------------

Mistake 3:
----------

Updating the prefix outside the correct logic.

The prefix should only contain characters common to all strings processed
so far.


===============================================================================
PATTERN LEARNED
===============================================================================

String Array

       ↓

Prefix Matching

       ↓

Compare characters at the same index

       ↓

Stop at mismatch


Important Java methods:

startsWith()
substring()
charAt()
length()


===============================================================================
INTERVIEW APPROACH
===============================================================================

If interviewer asks:

"Can you solve it without sorting?"


Answer:

Yes.

I can use vertical scanning.

I take the first string as the prefix and compare its characters with
the corresponding characters of all other strings.

As soon as a mismatch occurs, I stop and return the common part.


If interviewer asks:

"Can you give another approach?"


You can explain:

1. Sorting
2. Horizontal scanning
3. Vertical scanning
4. Trie


===============================================================================
BACKEND JAVA CONNECTION
===============================================================================

Prefix matching can be useful in backend development for:

• API route matching
• URL path matching
• Search/autocomplete
• File path processing
• Command parsing
• Trie-based lookup


Example:

/api/users
/api/users/123
/api/users/profile


Common prefix:

/api/users


===============================================================================
SIMILAR PROBLEMS
===============================================================================

• Longest Common Prefix
• Longest Common Substring
• Longest Common Subsequence
• Implement strStr()
• Search suggestions
• Trie / Prefix Tree
• Word Search


===============================================================================
FINAL INTERVIEW TAKEAWAY
===============================================================================

When you see:

"Find the longest common prefix"

Think:

                    PREFIX
                       ↓
             Compare all strings
                       ↓
              Find first mismatch
                       ↓
              Return common part


Best general approach:

Vertical Scanning


Time:

O(n × L)


Space:

O(1) auxiliary space.


===============================================================================
*/
