package y.w.algorithms.leetcode

import kotlin.test.assertEquals

/**
 * The Tribonacci sequence Tn is defined as follows:
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 * Given n, return the value of Tn.
 *
 * Or:
 *   Tn = Tn-3 + Tn-2 + Tn-1
 */

/**
 * Time: O(3^n)
 */
private fun recursion(n: Int): Int {
    if (n <= 1) return n
    if (n == 2) return 1

    return recursion(n - 3) + recursion(n - 2) + recursion(n -1)
}

private fun dpConstantSpace(n: Int): Int {
    var t0 = 0
    var t1 = 1
    var t2 = 1

    for (x in 3..n) {
        var t = t0 + t1 + t2
        t0 = t1
        t1 = t2
        t2 = t
    }
    return t2
}

fun main() {
    assertEquals(recursion(4), 4)
    assertEquals(recursion(25), 1389537)

    assertEquals(dpConstantSpace(4), 4)
    assertEquals(dpConstantSpace(25), 1389537)
}