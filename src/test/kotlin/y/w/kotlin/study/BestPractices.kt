package y.w.kotlin.study

import org.junit.*

fun utilityFunc() : String = "world"

fun String.world(value: String): String {
    return "$this $value"
}

data class Cart(var total: Double = 0.0, var numItems : Int = 0)

class BestPractices {
    @Test
    fun `Use Expression instead of If ELSE`(): Unit {
        val value = when("a") {
            "a" -> "it is a"
            "b" -> "it is b"
            else -> "it is none of them"
        }
    }

    @Test
    fun `Use top level functions or extension functions for utility function`() {
        println("hello".world(utilityFunc()))
    }

    @Test
    fun `Use Apply to group object method calls`() {
        Cart().apply {
            total = 100.0
            numItems = 5
        }
    }
}