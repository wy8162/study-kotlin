package y.w.kotlin.study

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

// val - getter is generated
// var - getter and setter are generated
// none - no accessor is generated

class C(name: String, var email: String) {
    private var fname: String

    init {
        this.fname = name
    }

    fun getName(): String = this.fname
    fun setName(name: String) { this.fname = name }
}

class SomeClass {
    var name: String = "123"
        private set   // Makes the setter private
}


class B(name: String)

class ClassGetterSetter {
    @Test
    fun t1() {
        val c = C("jake", "j@j.com")

        assertTrue(c.getName() == "jake")

        c.setName("john")
        assertTrue(c.getName() == "john")

        val b = B("j")

        // b.name is not accessible
        // b.name
    }
}