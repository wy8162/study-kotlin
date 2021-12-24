package y.w.algorithms.leetcode

import kotlin.test.assertTrue

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint
 * stopping you from robbing each of them is that adjacent houses have
 * security systems connected and it will automatically contact the police
 * if two adjacent houses were broken into on the same night. Given an integer
 * array nums representing the amount of money of each house, return the
 * maximum amount of money you can rob tonight without alerting the police.
 *
 * Given: ARR[2,7,9,3,1]
 * Answer: [2,9,1]
 *
 * Algorithm:
 *
 * DP approach
 *
 * Let DP(i) be the maximum value to rob from 0 to the ith house. There are two
 * possibilities: rob ith or not. If rob ith, then DP(i) = DP(i - 2) + ARR[i] because
 * we will not rob i-1 if we decide to rob i; if not, then DP(i) = DP(i-1). DP(i) needs
 * to be the greater one of both case.
 *
 * So the recurrence can be written as:
 *
 * DP(i) = max(DP(i-1), DP(i-2) + ARR[i])
 *
 * Base cases:
 *  DP(0) = ARR[0]
 *  DP(1) = max(DP(0), ARR[1])
 *
 * Here, i, the number of house, can be used as state to describe the scenario.
 */

private fun recursion(arr: Array<Int>, i: Int): Int {
    if (i == 0) return arr[0]
    if (i == 1) return maxOf(arr[0], arr[1])

    return maxOf(recursion(arr, i - 1), recursion(arr, i - 2) + arr[i])
}

private fun dpTopDown(arr: Array<Int>, i: Int, cache: MutableMap<Int, Int>): Int {
    if (i == 0) return arr[0]
    if (i == 1) return maxOf(arr[0], arr[1])

    if (cache.contains(i)) return cache[i]!!

    cache[i] = maxOf(dpTopDown(arr, i - 1, cache), dpTopDown(arr, i - 2, cache) + arr[i])
    return cache[i]!!
}

private fun dpTable(arr: Array<Int>): Int {
    val cache = mutableMapOf<Int, Int>()

    cache.put(0, arr[0])
    cache.put(1, maxOf(arr[0], arr[1]))

    for (i in 2..arr.size - 1)
        cache.put(i, maxOf(cache.get(i - 1)!!, cache.get(i - 2)!! + arr[i]))

    return cache.get(arr.size - 1)!!
}

fun main() {
    assertTrue(recursion(arrayOf(2,7,9,3,1), 4) == 12)
    assertTrue(dpTable(arrayOf(2,7,9,3,1)) == 12)
    assertTrue(dpTopDown(arrayOf(2,7,9,3,1), 4, mutableMapOf()) == 12)
}