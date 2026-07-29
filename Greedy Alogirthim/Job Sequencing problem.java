/*
===============================================================================
Problem: Job Sequencing Problem
Difficulty: Medium
Platform: GeeksforGeeks
===============================================================================

Problem Statement
-----------------
Given N jobs where every job has:

1. Job Id
2. Deadline
3. Profit

Each job takes exactly ONE unit of time.

Only one job can be performed at a time.

A job must be completed on or before its deadline.

Return:
1. Maximum number of jobs performed.
2. Maximum profit earned.

Example

Job      Deadline     Profit

1            2          100
2            1           19
3            2           27
4            1           25
5            3           15

Answer

Jobs Done = 3
Profit = 142

===============================================================================
Approach 1 : Greedy + Slot Array (Brute Force)
===============================================================================

Idea
----

Step 1
Sort jobs according to Profit in descending order.

Highest profit jobs are considered first.

Step 2

Create slots from

1 ... Maximum Deadline

Initially all slots are empty.

Example

Deadline = 4

Slots

1   2   3   4

Step 3

For every job

Try to place it in the latest available slot before its deadline.

Why latest slot?

Suppose

Deadline = 4

The job can be done in

1
2
3
4

If we place it in slot 4,

slots 1,2,3 remain free for jobs having earlier deadlines.

This is the Greedy Choice.

===============================================================================
Brute Force Code
===============================================================================
*/

import java.util.*;

class Job {

    int id;
    int deadline;
    int profit;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

class Solution {

    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {

        int n = deadline.length;

        ArrayList<Job> jobs = new ArrayList<>();

        int maxDeadline = 0;

        for (int i = 0; i < n; i++) {

            jobs.add(new Job(i + 1, deadline[i], profit[i]));

            maxDeadline = Math.max(maxDeadline, deadline[i]);
        }

        Collections.sort(jobs, (a, b) -> b.profit - a.profit);

        int slots[] = new int[maxDeadline + 1];

        Arrays.fill(slots, -1);

        int countJobs = 0;
        int totalProfit = 0;

        for (Job job : jobs) {

            for (int j = job.deadline; j >= 1; j--) {

                if (slots[j] == -1) {

                    slots[j] = job.id;

                    countJobs++;
                    totalProfit += job.profit;

                    break;
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        ans.add(countJobs);
        ans.add(totalProfit);

        return ans;
    }
}

/*
===============================================================================
Complexity
===============================================================================

Sorting

O(n log n)

Finding Slot

Worst Case

O(n × maxDeadline)

Space

O(maxDeadline)

This solution may give TLE for very large deadlines.

===============================================================================
Approach 2 : Greedy + Disjoint Set Union (Optimal)
===============================================================================

Why Brute Force becomes slow?

Suppose

Maximum Deadline = 100000

For every job

you may scan

100000

99999

99998

...

1

This becomes

O(n × maxDeadline)

which causes TLE.

-------------------------------------------------------------------------------
DSU Idea
-------------------------------------------------------------------------------

Instead of checking every slot,

directly jump to the latest available slot.

Suppose

Slots

1 2 3 4 5

Slot 5 becomes occupied.

Instead of searching

5
4
3
2
1

DSU immediately tells us

Latest free slot = 4

-------------------------------------------------------------------------------
Parent Array
-------------------------------------------------------------------------------

Initially

Parent

0 1 2 3 4 5

Every slot is its own parent.

After occupying slot 5

Parent

0 1 2 3 4 4

Meaning

Slot 5 is occupied.

Go to slot 4.

After occupying slot 4

Parent

0 1 2 3 3 4

find(5)

↓

4

↓

3

Returns

3

No searching required.

-------------------------------------------------------------------------------
Find Function
-------------------------------------------------------------------------------

Returns latest available slot.

int find(int x){

    if(parent[x]==x)
        return x;

    return parent[x]=find(parent[x]);
}

-------------------------------------------------------------------------------
Union
-------------------------------------------------------------------------------

If slot 5 becomes occupied

Connect

5

with

4

parent[5]=find(4)

Next time

find(5)

returns

4

===============================================================================
Optimal DSU Code
===============================================================================
*/

import java.util.*;

class Job {

    int id;
    int deadline;
    int profit;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

class Solution {

    int parent[];

    int find(int x) {

        if (parent[x] == x)
            return x;

        return parent[x] = find(parent[x]);
    }

    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {

        int n = deadline.length;

        ArrayList<Job> jobs = new ArrayList<>();

        int maxDeadline = 0;

        for (int i = 0; i < n; i++) {

            jobs.add(new Job(i + 1, deadline[i], profit[i]));

            maxDeadline = Math.max(maxDeadline, deadline[i]);
        }

        Collections.sort(jobs, (a, b) -> b.profit - a.profit);

        parent = new int[maxDeadline + 1];

        for (int i = 0; i <= maxDeadline; i++) {
            parent[i] = i;
        }

        int countJobs = 0;
        int totalProfit = 0;

        for (Job job : jobs) {

            int availableSlot = find(job.deadline);

            if (availableSlot > 0) {

                countJobs++;
                totalProfit += job.profit;

                parent[availableSlot] = find(availableSlot - 1);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        ans.add(countJobs);
        ans.add(totalProfit);

        return ans;
    }
}

/*
===============================================================================
Dry Run
===============================================================================

Deadline

2 1 2 1 3

Profit

100 19 27 25 15

After Sorting

Job1 ->100
Job3 ->27
Job4 ->25
Job2 ->19
Job5 ->15

Maximum Deadline

3

Slots

1 2 3

Initially

Empty Empty Empty

---------------------------------

Job1

Deadline =2

Place at slot2

Slots

_ 1 _

Profit=100

---------------------------------

Job3

Deadline=2

Slot2 occupied

Place at slot1

Slots

3 1 _

Profit=127

---------------------------------

Job4

Deadline=1

Slot1 occupied

Skip

---------------------------------

Job2

Deadline=1

Skip

---------------------------------

Job5

Deadline=3

Slots

3 1 5

Profit=142

Jobs Done=3

===============================================================================
Greedy Choice
===============================================================================

Always choose

Highest Profit Job

Then place it in

Latest Available Slot

This leaves earlier slots free for jobs having smaller deadlines.

===============================================================================
Common Mistakes I Made
===============================================================================

✓ Didn't understand why sorting by profit works.

✓ Tried placing jobs in earliest slot.

Correct approach

Latest possible slot.

✓ Didn't understand why latest slot is important.

Earlier slots must remain free.

✓ Got Time Limit Exceeded using slot searching.

Optimized using DSU.

===============================================================================
Pattern Learned
===============================================================================

Greedy

+

Sorting

+

Scheduling

+

DSU Optimization

===============================================================================
Similar Problems
===============================================================================

✓ N Meetings in One Room

✓ Activity Selection

✓ Meeting Rooms

✓ Interval Scheduling

✓ Maximum Non-overlapping Intervals

===============================================================================
Backend Java Applications
===============================================================================

• CPU Scheduling

• Cloud Task Scheduling

• Operating Systems

• Job Queue Management

• Batch Processing Systems

• Resource Allocation

===============================================================================
Complexity
===============================================================================

Brute Force

Time

O(n log n + n × maxDeadline)

Space

O(maxDeadline)

---------------------------------

DSU

Time

O(n log n)

Find Operation

Almost O(1)

Space

O(maxDeadline)

===============================================================================
Interview Takeaway
===============================================================================

Remember the greedy rule:

1. Sort jobs by profit (highest first).
2. Schedule each job as late as possible before its deadline.
3. If brute-force slot search is too slow, replace it with DSU to locate the latest available slot efficiently.

===============================================================================
*/
