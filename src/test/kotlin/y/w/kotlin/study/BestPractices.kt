package y.w.kotlin.study

import mu.KotlinLogging
import org.junit.jupiter.api.Test

fun utilityFunc(): String = "world"

fun String.world(value: String): String {
    return "$this $value"
}

data class Cart(var total: Double = 0.0, var numItems: Int = 0)

class BestPractices {
    private val logger = KotlinLogging.logger {}

    @Test
    fun `Use Expression instead of If ELSE`() {
        val value = when ("a") {
            "a" -> "it is a"
            "b" -> "it is b"
            else -> "it is none of them"
        }

        logger.info("successful")
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
