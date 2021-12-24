package y.w.seq

fun primes(start: Int): Sequence<Int> = sequence {
    var index = start
    while (true) {
        if (index > 1 && (2 until index).none { i -> index % i == 0 }) {
            yield(index)
        }
        index++
    }
}

fun main() {
    val iterator = primes(17).iterator()

    while (true) {
        val v = iterator.next()
        if (v > 30) break
        println(v)
    }
}
