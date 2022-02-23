package y.w.kotlin.study

import org.junit.*

class FunctionReturnLabe {
    private fun printAndStop() {
        val list = listOf("a", "b", "c", "stop", "d")
        list.forEach {
            if (it == "stop") return // Returns to outside printAndStop."d" will not be printed.
            else println(it)
        }
        println("1. This line WILL NOT be executed.")
    }

    private fun printAndStop2() {
        val list = listOf("a", "b", "c", "stop", "d")
        list.forEach {
            if (it == "stop") return@forEach // Just return to the outside of this closure. "d" will also be printed.
            else println(it)
        }
        println("2. This line executed.")
    }

    @Test
    fun t1() {
        printAndStop()
        printAndStop2()
    }
}