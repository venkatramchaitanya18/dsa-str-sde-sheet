/*
 * ============================================================================
 * Problem: Minimum Platforms
 * Difficulty: Medium
 * Platform: GeeksforGeeks
 * ============================================================================
 *
 * Problem Statement:
 * Given arrival and departure times of trains,
 * find the minimum number of platforms required
 * so that no train waits.
 *
 * ============================================================================
 * Optimal Approach (Two Pointers)
 * ============================================================================
 *
 * Idea:
 *
 * Sort both arrays.
 *
 * Compare:
 *
 * Next Arrival
 * vs
 * Next Departure
 *
 * If arrival happens first,
 * one more platform is needed.
 *
 * If departure happens first,
 * one platform becomes free.
 *
 * ============================================================================
 * Code
 * ============================================================================
 */

class Solution {

    public int minPlatform(int arr[], int dep[]) {

        Arrays.sort(arr);
        Arrays.sort(dep);

        int platforms = 0;
        int maxPlatforms = 0;

        int i = 0;
        int j = 0;

        while (i < arr.length && j < dep.length) {

            if (arr[i] <= dep[j]) {

                platforms++;
                maxPlatforms = Math.max(maxPlatforms, platforms);

                i++;
            } else {

                platforms--;
                j++;
            }
        }

        return maxPlatforms;
    }
}

/*
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Arrival
 *
 * 900 940 950 1100 1500 1800
 *
 * Departure
 *
 * 910 1120 1130 1200 1900 2000
 *
 * Event Timeline
 *
 * 900  Arrival
 * Platforms = 1
 *
 * 910 Departure
 * Platforms = 0
 *
 * 940 Arrival
 * Platforms = 1
 *
 * 950 Arrival
 * Platforms = 2
 *
 * 1100 Arrival
 * Platforms = 3
 *
 * 1120 Departure
 * Platforms = 2
 *
 * 1130 Departure
 * Platforms = 1
 *
 * 1200 Departure
 * Platforms = 0
 *
 * 1500 Arrival
 * Platforms = 1
 *
 * 1800 Arrival
 * Platforms = 2
 *
 * Maximum Platforms = 3
 *
 * ============================================================================
 * Why Two Pointers?
 * ============================================================================
 *
 * We don't care which train is arriving.
 *
 * We only care:
 *
 * Which event happens next?
 *
 * Arrival
 * or
 * Departure
 *
 * Arrival
 * →
 * Platforms++
 *
 * Departure
 * →
 * Platforms--
 *
 * ============================================================================
 * Common Mistakes I Made
 * ============================================================================
 *
 * ✗ Compared
 *
 *      arr[i]
 *      with
 *      dep[i-1]
 *
 * This is incorrect because arrivals and departures
 * are independent after sorting.
 *
 * ✗ Didn't understand why two pointers are needed.
 *
 * Correct idea:
 *
 * i -> Next Arrival
 *
 * j -> Next Departure
 *
 * ============================================================================
 * Pattern Learned
 * ============================================================================
 *
 * Two Sorted Arrays
 * +
 * Two Pointer Traversal
 *
 * Compare the next event from each array.
 *
 * ============================================================================
 * Similar Problems
 * ============================================================================
 *
 * ✓ Meeting Rooms II
 * ✓ Merge Two Sorted Arrays
 * ✓ Merge Intervals
 * ✓ Employee Free Time
 * ✓ Interval Scheduling
 *
 * ============================================================================
 * Backend Java Uses
 * ============================================================================
 *
 * Similar event-processing logic is used in:
 *
 * • CPU Scheduling
 * • Calendar systems
 * • Booking systems
 * • Airline scheduling
 * • Railway management
 * • Event processing pipelines
 *
 * ============================================================================
 * Complexity
 * ============================================================================
 *
 * Sorting : O(n log n)
 *
 * Traversal : O(n)
 *
 * Total : O(n log n)
 *
 * Space : O(1)
 * ============================================================================
 */
