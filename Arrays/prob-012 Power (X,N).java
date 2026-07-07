/*
 * ============================================================================
 *                           50. Pow(x, n)
 * ============================================================================
 *
 * Difficulty : Medium
 *
 * Topic      : Binary Search / Binary Exponentiation
 *
 * Time Complexity
 * ----------------
 * Brute Force : O(n)
 * Optimal     : O(log n)
 *
 * Space Complexity
 * ----------------
 * O(1)
 *
 * ============================================================================
 * Problem Statement
 * ============================================================================
 *
 * Implement pow(x, n), which calculates x raised to the power n.
 *
 * Example:
 *
 * Input:
 * x = 2.00000
 * n = 10
 *
 * Output:
 * 1024.00000
 *
 * ============================================================================
 * Approach 1 : Brute Force
 * ============================================================================
 *
 * Intuition
 * ----------
 *
 * Multiply x exactly n times.
 *
 * Example:
 *
 * x = 2
 * n = 5
 *
 * ans = 1
 *
 * ans = 1 × 2 = 2
 * ans = 2 × 2 = 4
 * ans = 4 × 2 = 8
 * ans = 8 × 2 = 16
 * ans = 16 × 2 = 32
 *
 * If n is negative,
 * simply return
 *
 * 1 / ans
 *
 * Time Complexity : O(n)
 *
 * Space Complexity : O(1)
 *
 * ---------------------------------------------------------------------------
 */

class BruteForceSolution {

    public double myPow(double x, int n) {

        long power = Math.abs((long) n);

        double ans = 1.0;

        // Multiply x exactly power times
        for (long i = 0; i < power; i++) {
            ans *= x;
        }

        // Handle negative powers
        if (n < 0) {
            return 1.0 / ans;
        }

        return ans;
    }
}

/*
 * ============================================================================
 * Why Brute Force is Not Efficient?
 * ============================================================================
 *
 * Suppose
 *
 * x = 2
 * n = 2,000,000,000
 *
 * We need about 2 billion multiplications.
 *
 * This causes Time Limit Exceeded (TLE).
 *
 * We need a faster approach.
 *
 * ============================================================================
 * Approach 2 : Binary Exponentiation (Fast Power)
 * ============================================================================
 *
 * Intuition
 * ----------
 *
 * Instead of multiplying x one by one,
 * reduce the exponent by half whenever possible.
 *
 * Observation
 *
 * If power is even
 *
 * x^8
 *
 * =
 *
 * (x²)^4
 *
 * We can square x
 * and divide power by 2.
 *
 * --------------------------------------------
 *
 * If power is odd
 *
 * x^9
 *
 * =
 *
 * x × x^8
 *
 * Multiply one x into the answer,
 * then reduce the power by 1.
 *
 * Repeat until power becomes 0.
 *
 * ============================================================================
 * Algorithm
 * ============================================================================
 *
 * Step 1
 *
 * Convert n into long.
 *
 * This prevents overflow when
 * n = Integer.MIN_VALUE.
 *
 * --------------------------------------------
 *
 * Step 2
 *
 * ans = 1
 *
 * --------------------------------------------
 *
 * Step 3
 *
 * While power > 0
 *
 * If power is even
 *
 *      x = x * x
 *      power = power / 2
 *
 * Else
 *
 *      ans = ans * x
 *      power--
 *
 * --------------------------------------------
 *
 * Step 4
 *
 * If n is negative
 *
 * return
 *
 * 1 / ans
 *
 * ============================================================================
 */

class Solution {

    public double myPow(double x, int n) {

        // Convert int to long to avoid overflow
        long power = Math.abs((long) n);

        // Stores the final answer
        double ans = 1.0;

        // Binary Exponentiation
        while (power > 0) {

            // If power is even,
            // square x and halve the power.
            if (power % 2 == 0) {

                x = x * x;
                power = power / 2;
            }

            // If power is odd,
            // multiply one x into the answer.
            else {

                ans = ans * x;
                power--;
            }
        }

        // Handle negative powers
        if (n < 0) {
            return 1.0 / ans;
        }

        return ans;
    }
}

/*
 * ============================================================================
 * Dry Run
 * ============================================================================
 *
 * Input
 *
 * x = 2
 * n = 10
 *
 * power = 10
 *
 * ans = 1
 *
 * ------------------------------------------------
 *
 * power = 10 (Even)
 *
 * x = 2 × 2 = 4
 *
 * power = 5
 *
 * ans = 1
 *
 * ------------------------------------------------
 *
 * power = 5 (Odd)
 *
 * ans = 1 × 4 = 4
 *
 * power = 4
 *
 * ------------------------------------------------
 *
 * power = 4 (Even)
 *
 * x = 4 × 4 = 16
 *
 * power = 2
 *
 * ------------------------------------------------
 *
 * power = 2 (Even)
 *
 * x = 16 × 16 = 256
 *
 * power = 1
 *
 * ------------------------------------------------
 *
 * power = 1 (Odd)
 *
 * ans = 4 × 256 = 1024
 *
 * power = 0
 *
 * Answer = 1024
 *
 * ============================================================================
 * Why Binary Exponentiation Works?
 * ============================================================================
 *
 * Every time power is even,
 * we reduce it by half.
 *
 * Example
 *
 * 1024
 *
 * ↓
 *
 * 512
 *
 * ↓
 *
 * 256
 *
 * ↓
 *
 * 128
 *
 * ↓
 *
 * ...
 *
 * Instead of reducing by 1,
 * we reduce by half.
 *
 * Therefore,
 * the number of iterations becomes
 *
 * log₂(n)
 *
 * ============================================================================
 * Time Complexity
 * ============================================================================
 *
 * Brute Force
 *
 * O(n)
 *
 * ---------------------------------------------------------------------------
 *
 * Binary Exponentiation
 *
 * O(log n)
 *
 * Space Complexity
 *
 * O(1)
 *
 * ============================================================================
 * Interview Follow-up
 * ============================================================================
 *
 * Q1. Why convert n into long?
 *
 * Because
 *
 * Integer.MIN_VALUE
 *
 * = -2147483648
 *
 * abs(Integer.MIN_VALUE)
 *
 * still overflows.
 *
 * Using long avoids this issue.
 *
 * ---------------------------------------------------------------------------
 *
 * Q2. Why square x?
 *
 * Because
 *
 * x^8
 *
 * =
 *
 * (x²)^4
 *
 * We reduce the exponent by half.
 *
 * ---------------------------------------------------------------------------
 *
 * Q3. Why multiply only when power is odd?
 *
 * Example
 *
 * x^9
 *
 * =
 *
 * x × x^8
 *
 * We first store one x in the answer,
 * then continue solving x^8.
 *
 * ============================================================================
 * Real-World Applications
 * ============================================================================
 *
 * 1. Cryptography
 *
 * Fast modular exponentiation is used in
 * RSA encryption, Diffie-Hellman Key Exchange,
 * and Digital Signatures.
 *
 * ---------------------------------------------------------------------------
 *
 * 2. Scientific Computing & Graphics
 *
 * Used for efficiently calculating
 * powers in simulations,
 * computer graphics,
 * machine learning,
 * and numerical algorithms.
 *
 * ============================================================================
 * Pattern Recognition
 * ============================================================================
 *
 * Whenever you see:
 *
 * ✔ Large power calculations
 *
 * ✔ x^n
 *
 * ✔ Modular Exponentiation
 *
 * ✔ Very large exponent
 *
 * Immediately think:
 *
 * Binary Exponentiation
 *
 * ============================================================================
 * Similar Problems
 * ============================================================================
 *
 * ✔ Pow(x, n)
 *
 * ✔ Super Pow
 *
 * ✔ Modular Exponentiation
 *
 * ✔ Count Good Numbers
 *
 * ✔ Fibonacci using Matrix Exponentiation
 *
 * ✔ Nth Power Problems
 *
 * ============================================================================
 */
