package y.w.kotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Basics {
    @Test
    fun t1() {
        assertThat("123".toInt()).isEqualTo(123)
        assertThat(1.shl(2)).isEqualTo(4)
        assertThat(arrayOf(1,2,3,4)).isSorted.containsExactly(1,2,3,4)
        "yang".forEach { println(it) }
    }
}