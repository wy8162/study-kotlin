package y.w.kotlin.study

import org.junit.jupiter.api.Test

internal open class BaseClassVisibility(
    internal var internalVar: String,
    private var privateVar: String,
    protected var protectedVar: String,
    var publicVar: String
)

class TestVisibility {
    @Test
    fun t1() {
        // Yes, we see this BaseClassVisibility
        val s = BaseClassVisibility(
    "internal", "private",
    "protected", "public")

        println(s.internalVar) // OK
        println(s.publicVar)   // OK
        //println(s.privateVar) - No
        //println(s.protectedVar) - No
    }

}
