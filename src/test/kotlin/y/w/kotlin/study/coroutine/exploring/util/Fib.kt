package y.w.kotlin.study.coroutine.exploring.util

fun fib(num: Int): Int {
    if (num < 2) return num

    return fib(num - 1) + fib(num -2)
}
