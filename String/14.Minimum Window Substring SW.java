/*
Problem: Minimum Window Substring
LeetCode: 76
Pattern: Sliding Window + HashMap

Approach:
Maintain a window [left, right] and a HashMap containing the
required frequency of each character from t. `count` tracks how
many characters from t have been satisfied, including duplicates.
When count == t.length(), the window is valid, so shrink it from
left and keep the smallest valid window.

Key fixes from my first attempt:
1. Use map.getOrDefault(ch, 0) to avoid null values.
2. Decrease the map frequency after processing a character.
3. While shrinking, increase the frequency again.
4. substring(startInd, startInd + minLen), because minLen is a
   length, not an ending index.

Time: O(n + m) average
Space: O(k), where k is the number of tracked characters.
*/

import java.util.*;

class Solution {
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();

        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int r = 0;
        int l = 0;
        int count = 0;
        int minLen = Integer.MAX_VALUE;
        int startInd = -1;

        while (r < n) {
            char ch = s.charAt(r);

            if (map.getOrDefault(ch, 0) > 0) {
                count++;
            }

            map.put(ch, map.getOrDefault(ch, 0) - 1);

            while (count == m) {
                char ch1 = s.charAt(l);

                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    startInd = l;
                }

                map.put(ch1, map.getOrDefault(ch1, 0) + 1);

                if (map.get(ch1) > 0) {
                    count--;
                }

                l++;
            }

            r++;
        }

        return startInd == -1 ? "" : s.substring(startInd, startInd + minLen);
    }
}

/*
DETAILED NOTES

Example: s = "ADOBECODEBANC", t = "ABC"

Initial map:
A -> 1
B -> 1
C -> 1

When a required character is found and its frequency is positive,
count increases. Then its frequency is decreased. Extra characters
can therefore have negative frequencies.

Example: t = "ABC", current window contains "AABC".
After both A characters, A can become -1. This means the window has
one extra A. When shrinking, restoring that A to 0 does not reduce
count. Restoring a required A from 0 to 1 does reduce count.

`count == m` means every required character occurrence is present,
not merely that all distinct characters are present.

Common mistake:
    if (map.get(ch) > 0)
can throw NullPointerException when ch is not in the map.
Use:
    if (map.getOrDefault(ch, 0) > 0)

Common substring mistake:
    s.substring(startInd, minLen)
is wrong because minLen is a length.
Correct:
    s.substring(startInd, startInd + minLen)

Interview explanation:
I use a sliding window with a frequency HashMap. I expand the right
pointer and track how many required character occurrences are
satisfied. Once all characters from t are satisfied, I shrink from
the left to find the smallest valid window. The answer is updated
while the window remains valid.

Related problems:
- Longest Substring Without Repeating Characters
- Longest K Substring
- Character Replacement
- Fruit Into Baskets
- Minimum Window Subsequence
*/
