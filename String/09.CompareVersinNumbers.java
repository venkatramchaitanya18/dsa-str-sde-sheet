/*
================================================================================
                    COMPARE VERSION NUMBERS - LEETCODE 165
================================================================================

Problem:
Given two version numbers, compare them.

Return:
    -1 -> version1 < version2
     0 -> version1 == version2
     1 -> version1 > version2

Examples:
    "1.01" vs "1.001" -> 0
    "1.0"  vs "1.0.1" -> -1
    "1.2"  vs "1.10"  -> -1

================================================================================
                              SOLUTION
================================================================================
*/

class Solution {
    public int compareVersion(String version1, String version2) {

        // Split strings by the dot character.
        // Dot is escaped because split() uses Regular Expressions.
        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        // Find the maximum number of version levels.
        int maxLength = Math.max(levels1.length, levels2.length);

        for (int i = 0; i < maxLength; i++) {

            // If a level does not exist, consider it as 0.
            int num1 = i < levels1.length
                    ? Integer.parseInt(levels1[i])
                    : 0;

            int num2 = i < levels2.length
                    ? Integer.parseInt(levels2[i])
                    : 0;

            // Compare the current version levels.
            if (num1 < num2) {
                return -1;
            }

            if (num1 > num2) {
                return 1;
            }
        }

        // All version levels are equal.
        return 0;
    }
}


/*
================================================================================
                              DETAILED EXPLANATION
================================================================================

1. SPLIT THE VERSION

Example:

    "1.2.10"

After:

    version1.split("\\.")

We get:

    ["1", "2", "10"]

Why "\\."?

Java String:
    "\\."

Regular Expression received by split():
    "\."

The dot (.) has a special meaning in Regular Expressions.
It normally means "any character".

Therefore, to split specifically on a dot, we escape it.

Wrong:
    split(".")

Correct:
    split("\\.")


================================================================================
2. COMPARE LEVEL BY LEVEL
================================================================================

Example:

    version1 = "1.2.10"
    version2 = "1.2.9"

Compare:

    1 == 1
    2 == 2
    10 > 9

Therefore:

    return 1;


================================================================================
3. MISSING LEVELS ARE TREATED AS 0
================================================================================

Example:

    version1 = "1.0"
    version2 = "1.0.1"

Arrays:

    levels1 = ["1", "0"]
    levels2 = ["1", "0", "1"]

At i = 2:

    version1 has no level
    therefore num1 = 0

    num2 = 1

So:

    0 < 1

Return:

    -1


================================================================================
4. WHY "1.01" AND "1.001" ARE EQUAL
================================================================================

After splitting:

    version1 = ["1", "01"]
    version2 = ["1", "001"]

Integer.parseInt():

    "01"  -> 1
    "001" -> 1

So:

    1 == 1

Therefore:

    return 0;


================================================================================
5. TERNARY OPERATOR
================================================================================

This:

    int num1 = i < levels1.length
            ? Integer.parseInt(levels1[i])
            : 0;

means:

    if (i < levels1.length)
        use the existing version level
    else
        use 0

It is equivalent to:

    int num1;

    if (i < levels1.length) {
        num1 = Integer.parseInt(levels1[i]);
    } else {
        num1 = 0;
    }


================================================================================
                              DRY RUN
================================================================================

Input:

    version1 = "1.0"
    version2 = "1.0.1"

Split:

    levels1 = ["1", "0"]
    levels2 = ["1", "0", "1"]

maxLength = 3


 i = 0

    num1 = 1
    num2 = 1

    Equal -> continue


 i = 1

    num1 = 0
    num2 = 0

    Equal -> continue


 i = 2

    num1 = 0    // missing level
    num2 = 1

    0 < 1

    return -1


================================================================================
                           COMMON MISTAKES
================================================================================

1. Using:

       version1.split(".")

   This is incorrect because "." is a Regular Expression metacharacter.

   Use:

       split("\\.")


2. Comparing strings directly.

   Version levels must be compared numerically.

   Use:

       Integer.parseInt()


3. Forgetting missing levels.

   Example:

       "1.0"
       "1.0.1"

   Missing levels should be treated as 0.


4. Not stopping when a difference is found.

   Once a level is different, the final answer is already known.


================================================================================
                              PATTERN TO REMEMBER
================================================================================

    SPLIT
       ↓
    CONVERT TO INTEGER
       ↓
    COMPARE LEVEL BY LEVEL
       ↓
    MISSING LEVEL = 0
       ↓
    RETURN -1 / 0 / 1


================================================================================
                              COMPLEXITY
================================================================================

Let:

    N = length of version1
    M = length of version2

Time Complexity:
    O(N + M)

Space Complexity:
    O(N + M)

The space is used by the arrays created after split().


================================================================================
                         INTERVIEW TAKEAWAY
================================================================================

The important ideas from this problem are:

1. Java String.split() uses Regular Expressions.
2. Special characters such as "." need escaping.
3. Version components must be compared numerically.
4. Missing components can be treated as 0.
5. Return immediately when a difference is found.

This is a good example of a String Parsing problem.


================================================================================
                         SIMILAR PROBLEMS
================================================================================

- String to Integer (atoi)
- Compare Strings
- Valid Number
- Repeated String Match
- Longest Common Prefix
- Roman to Integer


================================================================================
                       JAVA BACKEND CONNECTION
================================================================================

Version comparison appears in real software systems.

Examples:

- Comparing API versions
- Checking software/library versions
- Validating dependency versions
- Comparing application release versions

Example:

    Current version = "2.4.1"
    Required version = "2.3.5"

A backend service may need to determine whether the current
version satisfies the required version.

================================================================================
*/
