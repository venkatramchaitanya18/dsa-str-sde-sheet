import java.util.*;

class Solution {
    public int longestKSubstr(String s, int k) {
        int left = 0, right = 0, max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char r = s.charAt(right);
            map.put(r, map.getOrDefault(r, 0) + 1);
            while (map.size() > k) {
                char l = s.charAt(left);
                map.put(l, map.get(l) - 1);
                if (map.get(l) == 0) map.remove(l);
                left++;
            }
            if (map.size() == k) max = Math.max(max, right - left + 1);
            right++;
        }
        return max == 0 ? -1 : max;
    }
}

/*
LONGEST K SUBSTRING - GITHUB NOTES

Problem: Find the longest substring containing exactly k distinct characters.
Return -1 if no such substring exists.

Approach: Sliding Window + HashMap

The HashMap stores character -> frequency in the current window.
right expands the window. If distinct characters become > k,
move left forward and decrease frequencies until the window is valid.
Update the answer only when map.size() == k because the problem asks
for EXACTLY k distinct characters.

IMPORTANT BUG FIX:
Use map.getOrDefault(r, 0) + 1, not 1 + 1.
The first occurrence must have frequency 1.

Example:
s = "aabacbebebe", k = 3
Answer = 7

Complexity:
Time: O(n)
Space: O(k) approximately (or bounded by the character set).

Pattern:
Expand right -> add character -> shrink while distinct > k ->
when distinct == k, update max.

Common mistake:
map.size() means NUMBER OF DISTINCT CHARACTERS, not total frequency.
Example: {a=3,b=2,c=1} has map.size() = 3.

Interview explanation:
“I use a sliding window with a HashMap to maintain character frequencies.
I expand the right pointer and add each character. If the window contains
more than k distinct characters, I shrink it from the left while reducing
frequencies and removing characters whose frequency becomes zero. Whenever
there are exactly k distinct characters, I update the maximum length.”

Related problems:
- Longest Substring Without Repeating Characters
- Longest Substring with At Most K Distinct Characters
- Fruit Into Baskets
- Minimum Window Substring
- Longest Repeating Character Replacement
*/
