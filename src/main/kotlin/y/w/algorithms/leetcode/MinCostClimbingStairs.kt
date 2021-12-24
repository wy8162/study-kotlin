package y.w.algorithms.leetcode

import kotlin.test.assertEquals

/**
 * You are given an integer array cost where cost[i] is the cost
 * of ith step on a staircase. Once you pay the cost,
 * you can either climb one or two steps. You can either start
 * from the step with index 0, or the step with index 1. Return the minimum
 * cost to reach the top of the floor.
 *
 * This is a DP issue because
 * 1) it is to find out the optimal value - the minimum cost to climb the staircase.
 * 2) let DP(i) be the total cost to reach ith step. There are two ways to reach ith
 *    step: from i-1 with one step or from i - 2 with two steps.
 *
 * The recurrence can be denoted as below:
 *    DP(i) = min(cost[i-1] + DP(i-1), cost[i-2] + DP(i-2))
 *
 * i starts from the size of cost because we need to step to the top.
 *
 * Base cases:
 *    because we can start from 0 or 1, so these steps have zero cost.
 *    DP(0) = 0
 *    DP(1) = 0
 */

private fun recursion(cost: Array<Int>, i: Int): Int {
    if (i <= 1) return 0

    return minOf(cost[i - 1] + recursion(cost, i - 1), cost[i - 2] + recursion(cost, i - 2))
}

private fun dpTopDown(cost: Array<Int>, cache: MutableMap<Int, Int>, i: Int): Int {
    if (i <= 1) return 0

    if (cache.containsKey(i)) return cache[i]!!

    cache[i] = minOf(cost[i - 1] + recursion(cost, i - 1), cost[i - 2] + recursion(cost, i - 2))

    return cache[i]!!
}

private fun dpBottomUp(cost: Array<Int>): Int {
    val cache = mutableMapOf<Int, Int>()

    cache[0] = 0
    cache[1] = 0

    for (x in 2..cost.size) {
        cache[x] = minOf(cost[x - 1] + cache[x - 1]!!, cost[x - 2] + cache[x - 2]!!)
    }

    return cache[cost.size]!!
}

/**
 * Constant space
 */
private fun dpBottomUp2(cost: Array<Int>): Int {
    var oneStep = 0 // Step 0
    var twoStep = 0 // Step 1

    for (x in 2..cost.size) {
        var newCost = minOf(cost[x - 1] + oneStep, cost[x - 2] + twoStep)
        twoStep = oneStep
        oneStep = newCost
    }

    return oneStep
}

fun main() {
    assertEquals(recursion(arrayOf(10, 15, 20), 3), 15)
    assertEquals(recursion(arrayOf(1,100,1,1,1,100,1,1,100,1), 10), 6)

    assertEquals(dpTopDown(arrayOf(10, 15, 20), mutableMapOf(), 3), 15)
    assertEquals(dpTopDown(arrayOf(1,100,1,1,1,100,1,1,100,1), mutableMapOf(), 10), 6)

    assertEquals(dpBottomUp(arrayOf(10, 15, 20)), 15)
    assertEquals(dpBottomUp(arrayOf(1,100,1,1,1,100,1,1,100,1)), 6)

    assertEquals(dpBottomUp2(arrayOf(10, 15, 20)), 15)
    assertEquals(dpBottomUp2(arrayOf(1,100,1,1,1,100,1,1,100,1)), 6)
}
