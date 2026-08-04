/*
===============================================================================
Problem: Fractional Knapsack
Difficulty: Medium
Platform: GeeksforGeeks
===============================================================================

Code
----

class Product {

    int value;
    int weight;
    double valuePerWeight;

    Product(int value, int weight) {
        this.value = value;
        this.weight = weight;
        this.valuePerWeight = (double) value / weight;
    }
}

class Solution {

    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {

        ArrayList<Product> products = new ArrayList<>();

        for (int i = 0; i < val.length; i++) {
            products.add(new Product(val[i], wt[i]));
        }

        Collections.sort(products,
                (a, b) -> Double.compare(b.valuePerWeight, a.valuePerWeight));

        double maxValue = 0;

        for (Product p : products) {

            if (capacity == 0)
                break;

            if (p.weight <= capacity) {

                maxValue += p.value;
                capacity -= p.weight;

            } else {

                maxValue += p.valuePerWeight * capacity;
                capacity = 0;
            }
        }

        return maxValue;
    }
}

===============================================================================
Problem Statement
===============================================================================

Given

Value Array

Weight Array

Knapsack Capacity

Find the maximum value that can be placed inside the knapsack.

Unlike the 0/1 Knapsack,

you are allowed to take

FULL ITEM

or

ANY FRACTION OF AN ITEM.

===============================================================================
Example
===============================================================================

Value

60 100 120

Weight

10 20 30

Capacity

50

Answer

240

===============================================================================
Optimal Approach (Greedy)
===============================================================================

Idea

Every item has

Value Per Unit Weight

Value / Weight

Take the item having the highest value per kilogram first.

If the whole item fits,

take it.

Otherwise,

take only the remaining fraction.

===============================================================================
Algorithm
===============================================================================

Step 1

Compute

Value Per Weight

for every item.

Step 2

Store

Value

Weight

ValuePerWeight

inside an object.

Step 3

Sort all items in descending order of

ValuePerWeight.

Step 4

Traverse the sorted list.

If the item completely fits,

take the whole item.

Else

take only the required fraction

and stop.

===============================================================================
Dry Run
===============================================================================

Values

60 100 120

Weights

10 20 30

Capacity

50

--------------------------------

Value Per Weight

60/10 = 6

100/20 = 5

120/30 = 4

Sorted

6

5

4

--------------------------------

Capacity = 50

Take Item 1

Weight =10

Capacity =40

Profit =60

--------------------------------

Take Item 2

Weight =20

Capacity =20

Profit =160

--------------------------------

Item 3

Weight =30

Only 20 weight can be taken.

Fraction Value

4 × 20

=

80

Profit

160 + 80

=

240

===============================================================================
Why Greedy Works?
===============================================================================

Suppose

Item A

Value Per Weight =10

Item B

Value Per Weight =5

Every kilogram of Item A gives more value.

So taking Item A first can never decrease the answer.

This is the Greedy Choice Property.

Always take the highest value per unit weight first.

===============================================================================
Why Double?
===============================================================================

Wrong

int valuePerWeight

Example

100 / 30

=

3

Correct

3.3333

Always use

double

Otherwise

sorting becomes incorrect.

===============================================================================
Common Mistakes I Made
===============================================================================

✓ Used integer division.

100 / 30

became

3

instead of

3.333.

------------------------------------------------

✓ Stored

valuePerWeight

as int.

Should be

double.

------------------------------------------------

✓ Sorted the Product list

but later accessed

val[i]

wt[i]

instead of

products.get(i).

------------------------------------------------

✓ Fraction calculation

Wrong

value / weight * remainingCapacity

Correct

valuePerWeight * remainingCapacity

===============================================================================
Pattern Learned
===============================================================================

Greedy

+

Sorting

+

Custom Comparator

===============================================================================
Similar Problems
===============================================================================

✓ Assign Cookies

✓ Job Sequencing

✓ Maximum Units on a Truck

✓ Boats to Save People

✓ Activity Selection

===============================================================================
Backend Java Applications
===============================================================================

Similar strategy is used in

• Cargo Loading

• Cloud Resource Allocation

• Memory Allocation

• Budget Optimization

• Logistics

• Warehouse Packing

===============================================================================
Complexity
===============================================================================

Sorting

O(n log n)

Traversal

O(n)

Overall

O(n log n)

Space

O(n)

===============================================================================
Interview Takeaway
===============================================================================

Whenever you see

You can take a fraction

Think immediately

Greedy.

Sort by

Value / Weight

Take the highest ratio first.

This is the optimal solution for Fractional Knapsack.

===============================================================================
