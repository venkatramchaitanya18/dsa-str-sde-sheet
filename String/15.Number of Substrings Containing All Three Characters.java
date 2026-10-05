/*
Problem: Number of Substrings Containing All Three Characters
LeetCode: 1358
Pattern: Last Seen Index / Counting Substrings

Approach:
Keep the last index of a, b, and c. At every index i, if all
three have appeared, min(lastSeen[a], lastSeen[b], lastSeen[c]) + 1
is the number of valid substrings ending at i.
*/

import java.util.*;

class Solution {
    public int numberOfSubstrings(String s) {
        int lastSeen[] = new int[3];
        Arrays.fill(lastSeen, -1);
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            lastSeen[ch - 'a'] = i;

            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                count += Math.min(Math.min(lastSeen[0], lastSeen[1]), lastSeen[2]) + 1;
            }
        }
        return count;
    }
}

/*
DETAILED EXPLANATION

1. lastSeen stores the latest index of each character:
   a -> lastSeen[0], b -> lastSeen[1], c -> lastSeen[2].

2. Arrays.fill(lastSeen, -1) means the character has not appeared yet.

3. ch - 'a' maps a,b,c to indexes 0,1,2.

4. Once all three characters have appeared, take the minimum of their
   last-seen indexes. If that minimum is x, starting positions 0..x
   all create valid substrings ending at the current index, so add x+1.

Dry run for "abcabc":
 i=0: [0,-1,-1] -> 0
 i=1: [0,1,-1]  -> 0
 i=2: [0,1,2]   -> +1 = 1
 i=3: [3,1,2]   -> +2 = 3
 i=4: [3,4,2]   -> +3 = 6
 i=5: [3,4,5]   -> +4 = 10

Answer = 10.

TIME: O(n)
SPACE: O(1)

INTERVIEW TAKEAWAY:
Instead of generating every substring, count valid substrings ending
at each right index using the minimum last-seen position.

COMMON MISTAKES:
- Forgetting to initialize with -1.
- Using maximum instead of minimum.
- Counting only one substring when multiple starting positions work.
- Generating every substring unnecessarily.

RELATED PATTERNS:
- Minimum Window Substring
- Longest K Substring
- Longest Substring Without Repeating Characters
- Count Subarrays with Exactly K Distinct Integers

REAL-WORLD CONNECTION:
The same last-seen-index technique can count continuous ranges in
logs or event streams that contain all required event types.
*/
