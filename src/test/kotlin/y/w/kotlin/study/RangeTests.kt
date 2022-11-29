package y.w.kotlin.study

import org.junit.jupiter.api.Test
import kotlin.test.*

class RangeTests {
    @Test
    fun t1() {
        val range1 = 1..9
        for (k in range1) {
            println(k)
        }

        val range2 = "aa".."dd"
        assertTrue { "bc" in range2 }

        val check = "bc" in range2
        assertTrue { check }
    }
}


