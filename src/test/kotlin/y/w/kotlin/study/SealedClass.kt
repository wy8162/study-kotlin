package y.w.kotlin.study

import org.junit.*

sealed class BaseSealedClass(val state: String) {
    class SubSealed1(val v: String): BaseSealedClass("1")
    class SubSealed2(val v: String): BaseSealedClass("2")
}

class TestSealed {
    @Test
    fun t1() {
        val s = BaseSealedClass.SubSealed1("a")
        check(s)
    }

    private fun check(s: BaseSealedClass) {
        when (s) {
            is BaseSealedClass.SubSealed1 -> println("1")
            is BaseSealedClass.SubSealed2 -> println("2")
            // No need to have "else".
        }
    }
}