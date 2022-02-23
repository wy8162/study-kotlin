package y.w.kotlin.study2

import org.junit.*
import y.w.kotlin.study.*

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

