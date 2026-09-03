/*
============================================================
Problem: Sort Characters By Frequency
LeetCode: 451
Difficulty: Medium
Topic: Strings / HashMap / Bucket Sort
============================================================

PROBLEM STATEMENT
-----------------
Given a string s, sort it in decreasing order based on the
frequency of each character.

If multiple answers are possible, return any valid answer.

Example:
Input:  "tree"
Output: "eert" or "eetr"

Frequency:
e -> 2
t -> 1
r -> 1


============================================================
APPROACH 1: HASHMAP + FREQUENCY SCANNING
============================================================

IDEA
----
1. Store each character and its frequency in a HashMap.
2. Start from the highest possible frequency (s.length()).
3. For every frequency, scan all HashMap entries.
4. If a character has that frequency, append it frequency
   number of times.

This is the approach we discussed first.

CODE
----
*/

import java.util.*;

class FrequencySort {

    /*
     * APPROACH 1
     * HashMap + Frequency Scanning
     */
    public String frequencySortHashMap(String s) {

        // Step 1: Count frequency of every character
        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        // Step 2: Check frequencies from highest to lowest
        for (int freq = s.length(); freq >= 1; freq--) {

            // Check every character in the HashMap
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {

                char ch = entry.getKey();
                int count = entry.getValue();

                if (count == freq) {

                    // Add character 'freq' times
                    for (int i = 0; i < freq; i++) {
                        sb.append(ch);
                    }
                }
            }
        }

        return sb.toString();
    }


    /*
    ========================================================
    APPROACH 2
    HASHMAP + BUCKET SORT
    ========================================================

    IDEA
    ----
    Instead of repeatedly scanning the HashMap for every
    frequency, create buckets.

    bucket[frequency] stores all characters having that
    frequency.

    Example:
    s = "aaabbc"

    Frequencies:
    a -> 3
    b -> 2
    c -> 1

    Buckets:

    bucket[1] -> [c]
    bucket[2] -> [b]
    bucket[3] -> [a]

    Then traverse buckets from highest frequency to lowest.
    ========================================================
    */

    public String frequencySortBucket(String s) {

        // Step 1: Count frequency of every character
        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        /*
         * bucket[i] means:
         * characters occurring exactly i times.
         *
         * We need s.length() + 1 because the maximum
         * possible frequency of one character is s.length().
         */
        List<Character>[] bucket = new ArrayList[s.length() + 1];

        // Step 2: Put each character into its frequency bucket
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {

            char ch = entry.getKey();
            int freq = entry.getValue();

            // Create the list only when this bucket is needed
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }

            bucket[freq].add(ch);
        }

        // Step 3: Build answer from highest frequency to lowest
        StringBuilder sb = new StringBuilder();

        for (int freq = bucket.length - 1; freq >= 1; freq--) {

            if (bucket[freq] != null) {

                // Get every character having this frequency
                for (char ch : bucket[freq]) {

                    // Append the character 'freq' times
                    for (int i = 0; i < freq; i++) {
                        sb.append(ch);
                    }
                }
            }
        }

        return sb.toString();
    }
}


/*
============================================================
PROBLEM EXPLANATION
============================================================

For:
s = "tree"

Frequency Map:

t -> 1
r -> 1
e -> 2

We want characters with higher frequency first.

Therefore:

e -> "ee"
t -> "t"
r -> "r"

Possible answer:
"eetr"


============================================================
IMPORTANT JAVA CONCEPT
============================================================

Line:

List<Character>[] bucket = new ArrayList[s.length() + 1];

This creates an ARRAY OF LIST REFERENCES.

Initially:

bucket[0] -> null
bucket[1] -> null
bucket[2] -> null
bucket[3] -> null
...

It does NOT create an ArrayList at every position.

Therefore, before doing:

bucket[freq].add(ch);

we must make sure the list exists:

if (bucket[freq] == null) {
    bucket[freq] = new ArrayList<>();
}

Then:

bucket[freq].add(ch);


============================================================
WHY bucket[freq] == null?
============================================================

Suppose:

freq = 3

Initially:

bucket[3] -> null

If we directly write:

bucket[3].add('a');

Java tries:

null.add('a');

This causes:

NullPointerException

So we first create:

bucket[3] = new ArrayList<>();

Now:

bucket[3] -> []

Then:

bucket[3].add('a');

Now:

bucket[3] -> [a]


============================================================
DRY RUN OF BUCKET APPROACH
============================================================

Input:

"aaabbc"

Frequency Map:

a -> 3
b -> 2
c -> 1

Create buckets:

bucket[1] -> [c]
bucket[2] -> [b]
bucket[3] -> [a]

Now traverse backwards:

freq = 3
a has frequency 3
append aaa

StringBuilder:
"aaa"

freq = 2
b has frequency 2
append bb

StringBuilder:
"aaabb"

freq = 1
c has frequency 1
append c

StringBuilder:
"aaabbc"


============================================================
APPROACH COMPARISON
============================================================

Approach 1:
HashMap + Frequency Scanning

Flow:

String
  ↓
HashMap<Character, Integer>
  ↓
frequency = n → 1
  ↓
scan HashMap for every frequency
  ↓
StringBuilder

Time Complexity:
O(n * k)

where:
n = length of string
k = number of unique characters

Worst case:
O(n^2)


Approach 2:
HashMap + Bucket Sort

Flow:

String
  ↓
HashMap<Character, Integer>
  ↓
Create buckets based on frequency
  ↓
Traverse buckets from high → low
  ↓
StringBuilder

Time Complexity:
O(n)

Space Complexity:
O(n)


============================================================
COMMON MISTAKE
============================================================

WRONG:

int arr[] = new int[125];

for (...) {
    arr[s.charAt(i)]++;
}

Arrays.sort(arr);

After Arrays.sort(arr), the index no longer represents the
original character.

Before sorting:

index = character
value = frequency

After sorting:

index = only sorted position
value = frequency

Therefore, you lose the character-frequency relationship.

That is why HashMap or another structure that keeps the
character together with its frequency is useful.


============================================================
KEY PATTERN TO REMEMBER
============================================================

Frequency Sort:

1. Count frequency.
2. Keep character + frequency together.
3. Sort/group by frequency.
4. Build answer.

For the optimized solution:

Frequency Counting
        +
Bucket Sort
        +
StringBuilder


============================================================
INTERVIEW TAKEAWAY
============================================================

If interviewer asks:

"Can you improve the HashMap scanning solution?"

Think:

"Instead of checking every character for every frequency,
I'll directly place each character into a bucket based on
its frequency."

That gives the bucket-sort solution.


============================================================
SIMILAR PROBLEMS
============================================================

1. Top K Frequent Elements
2. Top K Frequent Words
3. Sort Characters By Frequency
4. Group Anagrams
5. Find All Anagrams in a String
6. First Unique Character in a String


============================================================
BACKEND JAVA CONNECTION
============================================================

Frequency counting using HashMap is useful when processing:

- API request statistics
- Log analysis
- Word/character frequency
- Event counting
- User activity counts
- Product/category counts

Example:

HashMap<String, Integer>

can be used to count how many times an event occurs.

The same pattern appears frequently in Java backend
applications when aggregating data.


============================================================
FINAL RECOMMENDATION
============================================================

For learning:

Understand Approach 1 first.

Then learn Approach 2.

For interviews:

Prefer the Bucket Sort approach when the constraints make
it suitable because it gives O(n) time.

============================================================
*/
