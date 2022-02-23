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

private fun funcReturn1(s: List<String>): Unit {
    println("Start function 1")
    s.forEach {
        if (it.startsWith("ABC"))
            println(it)
        else
            return
    }

    println("Done in main function 1")
}

private fun funcReturn2(s: List<String>): Unit {
    println("Start function 2")
    s.forEach {
        if (it.startsWith("ABC"))
            println(it)
        else
            return@forEach
    }

    println("Done in main function2")
}

fun main() {
    val iterator = primes(17).iterator()

    while (true) {
        val v = iterator.next()
        if (v > 30) break
        println(v)
    }

    funcReturn1(listOf("1", "ABC123", "2", "ABC567"))
    funcReturn2(listOf("1", "ABC123", "2", "ABC567"))
}
