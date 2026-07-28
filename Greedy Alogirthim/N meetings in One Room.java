/*
 * ============================================================================
 * Problem: N Meetings in One Room
 * Difficulty: Medium
 * Platform: GeeksforGeeks
 * ============================================================================
 *
 * Problem Statement:
 * Given the start and end times of N meetings, schedule the maximum number
 * of meetings in one room such that no two meetings overlap.
 *
 * Return the meeting numbers that can be performed.
 *
 * ============================================================================
 * Optimal Greedy Approach
 * ============================================================================
 *
 * Algorithm:
 *
 * 1. Store every meeting as:
 *      - Start Time
 *      - End Time
 *      - Original Position
 *
 * 2. Sort meetings according to their ending time.
 *
 * 3. Always select the meeting that finishes first.
 *
 * 4. Update the room's free time.
 *
 * 5. If the next meeting starts after the room becomes free,
 *    select that meeting.
 *
 * ============================================================================
 * Code
 * ============================================================================
 */

class Solution {

    static class Meeting {
        int start;
        int end;
        int pos;

        Meeting(int start, int end, int pos) {
            this.start = start;
            this.end = end;
            this.pos = pos;
        }
    }

    public ArrayList<Integer> maxMeetings(int[] s, int[] f) {

        int n = s.length;

        ArrayList<Meeting> meetings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            meetings.add(new Meeting(s[i], f[i], i + 1));
        }

        Collections.sort(meetings, (a, b) -> {
            if (a.end == b.end)
                return a.pos - b.pos;
            return a.end - b.end;
        });

        ArrayList<Integer> ans = new ArrayList<>();

        ans.add(meetings.get(0).pos);

        int freeTime = meetings.get(0).end;

        for (int i = 1; i < n; i++) {

            if (meetings.get(i).start > freeTime) {
                ans.add(meetings.get(i).pos);
                freeTime = meetings.get(i).end;
            }
        }

        // Required only for GFG output format
        Collections.sort(ans);

        return ans;
    }
}

/*
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Start : [1,3,0,5,8,5]
 * End   : [2,4,6,7,9,9]
 *
 * After Sorting:
 *
 * Meeting 1 -> End = 2
 * Meeting 2 -> End = 4
 * Meeting 3 -> End = 6
 * Meeting 4 -> End = 7
 * Meeting 5 -> End = 9
 * Meeting 6 -> End = 9
 *
 * Select Meeting 1
 * Room Free = 2
 *
 * Meeting 2
 * Start = 3 > 2
 * Select
 *
 * Room Free = 4
 *
 * Meeting 3
 * Start = 0 <= 4
 * Skip
 *
 * Meeting 4
 * Start = 5 > 4
 * Select
 *
 * Meeting 5
 * Start = 8 > 7
 * Select
 *
 * Answer
 *
 * [1,2,4,5]
 *
 * ============================================================================
 * Why Greedy Works?
 * ============================================================================
 *
 * A meeting that finishes earlier leaves more room
 * for future meetings.
 *
 * Therefore choosing the earliest finishing meeting
 * always gives the maximum answer.
 *
 * ============================================================================
 * Common Mistakes I Made
 * ============================================================================
 *
 * ✓ Didn't understand Comparator.
 *
 * ✓ Didn't know why sorting by end time works.
 *
 * ✓ Didn't know why GFG requires:
 *
 *      Collections.sort(ans);
 *
 * Because GFG expects meeting numbers
 * in ascending order.
 *
 * ============================================================================
 * Pattern Learned
 * ============================================================================
 *
 * Sort + Greedy
 *
 * Always pick the activity that finishes first.
 *
 * ============================================================================
 * Similar Problems
 * ============================================================================
 *
 * ✓ Activity Selection
 * ✓ Job Sequencing
 * ✓ Non-overlapping Intervals
 * ✓ Meeting Rooms
 * ✓ Meeting Rooms II
 *
 * ============================================================================
 * Complexity
 * ============================================================================
 *
 * Time  : O(n log n)
 * Space : O(n)
 * ============================================================================
 */
