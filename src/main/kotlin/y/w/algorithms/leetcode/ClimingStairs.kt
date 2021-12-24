package y.w.algorithms.leetcode

import kotlin.test.assertTrue

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct
 * ways can you climb to the top?
 *
 * Example 1:
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Algorithm Analysis
 * Let DP(i) denotes the maximum distinct steps. There are two ways to reach step i: 1 way
 * from DP(i-1) or 2 ways from DP(i-2).
 *
 * Here "i" is the variable or state used to describe the scenario for each step.
 *
 * 1. Overlapping sub-problems - the problem DP(i) depends on the sub-problems DP(i-1) and DP(i-2). They overlap.
 * 2. Optimal sub-structure - DP(i) is a optimal solution while DP(i-1) and DP(i-2) are also optimal solutions.
 *    Put it in the other way, computing DP(i) depends on DP(i-1) and DP(i-2).
 *
 * Recurrence:
 *    DP(i) = DP(i-1) + DP(i-2)
 *    base cases: DP(1) = 1, DP(2) = 2
 *
 */

private fun recursive(n: Int): Int {
    return if (n <= 2) n else recursive(n - 1) + recursive(n - 2)
}

private fun dp(n: Int, cache: MutableMap<Int, Int>): Int {
    if (n <= 2) return n

    cache.putIfAbsent(n, dp(n - 1, cache) + dp(n - 2, cache))
    return cache[n]!!
}

private fun dpTable(n: Int): Int {
    if (n == 1) return 1

    val table = IntArray(n + 1)
    table[0] = 1 // 1 way if there is 0 step.
    table[1] = 1
    table[2] = 2

    for (i in 2..n) {
        table[i] = table[i - 1] + table[i - 2]
    }

    return table[n]
}

fun main() {
    assertTrue(recursive(2) == 2) // Expects 2
    assertTrue(recursive(3) == 3) // Expects 3

    assertTrue(dp(2, mutableMapOf()) == 2)
    assertTrue(dp(3, mutableMapOf()) == 3)

    assertTrue(dpTable(2) == 2)
    assertTrue(dpTable(3) == 3)
}
