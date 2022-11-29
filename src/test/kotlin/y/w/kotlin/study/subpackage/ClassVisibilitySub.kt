package y.w.kotlin.study.subpackage

import org.junit.jupiter.api.Test
import y.w.kotlin.study.BaseClassVisibility


class TestVisibility {
    @Test
    fun t1() {
        val s = BaseClassVisibility(
            "internal", "private",
            "protected", "public")

        println(s.internalVar) // OK
        println(s.publicVar)   // OK
        //println(s.privateVar) - No
        //println(s.protectedVar) - No
    }

}
