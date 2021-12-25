package y.w.kotlin

import org.junit.Test

/**
 * Pair - a tuple of two values
 * Triple - a tuple of three values
 * Array - indexed fixed sized collection of objects and primitives
 * List
 * Set
 * Map
 */
class KotlinCollections {
    @Test
    fun t1() {
        // Conveniences
        val names = listOf("Apple", "Peach", "Strawberry")
        for ((index, value) in names.withIndex())
            println("$index $value")
    }

    @Test
    fun t2() {
        Pair(1,2)
        Pair("a", "b")

        // This also creates a pair
        "a" to "b"

        // if (p is Pair) println("yes")
    }
}