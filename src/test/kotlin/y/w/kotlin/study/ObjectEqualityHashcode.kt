package y.w.kotlin.study

import org.junit.*

// Kotlin automatically creates getters and setters, equals, hashCode.
class Aa(val name: String, val postCode: Int)

// Customize it
class Bb(val name: String, val num: Int) {
    override fun toString() = "B[name=$name, num=$num]"
}

class ObjectEqualityHashcode {
    @Test
    fun t1(): Unit {
        val a1  = Aa("a1", 1)
        val a11 = Aa("a1", 1)

        val b1 = Bb("b1", 1)

        println(a1) // y.w.kotlin.study.Aa@760bb833
        println(b1) // B[name=b1, num=1]

        println(a1 == a11)  // false. Like Java equals
        println(a1 === a11) // false. Like Java ==, comparing reference
        println(a1 === a1)  // true. Like Java ==, comparing reference
    }
}