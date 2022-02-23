package y.w.kotlin.study

import org.junit.*

/**
 * Kotlin autogenerate universal methods: equals, hashCode and toString
 * for data class.
 */

// Kotlin automatically creates getters and setters, equals, hashCode.
// Kotlin also generates copy method to clone data class object.
data class Aaa(val name: String, val postCode: Int)
data class Aaa2(var name: String, val postCode: Int)

class DataClasses {
    @Test
    fun t1(): Unit {
        val a1  = Aaa("a1", 1)
        val a11 = Aaa("a1", 1)

        println(a1); // Aaa(name=a1, postCode=1)


        println(a1 == a11)  // true. Like Java equals
        println(a1 === a11) // false. Like Java ==, comparing reference
        println(a1 === a1)  // true. Like Java ==, comparing reference

        // Cloning
        val a12 = a1.copy()
        println(a12)

        val aa2 = Aaa2("aa2", 2).copy()
        println(aa2)
    }
}