package y.w.kotlin

import org.assertj.core.api.Assertions.*
import org.junit.*
import java.nio.file.*

data class Car(var gas: Int, val maker: String) {
    fun run(): Unit {
        if (gas <= 0) println("No gas, can't run")
        else println("Yeah, running...")
    }

    fun carry(): Unit {}
}

class ScopeFunctions {
    /**
     * APPLY
     * - access to this pointer.
     * - returns the original instance.
     * - used to initialize an instance.
     */
    @Test
    fun testApply(): Unit {
        var car = Car(0, "Honda")

        car.run() // It doesn't run because of empty tank.

        // Using apply to initialize the instance.
        // It can access to the instance directly without referencing the instance.
        car.apply { gas = 100 }.run()
    }

    /**
     * LET
     * - Access to "it"
     * - returns the result of the closure.
     * - used to run some code and then return different value.
     * - throw away the original instance.
     */
    @Test
    fun letTest() {
        var result = Car(0, "Toyota")
            .let {
                it.gas = 100
                it.carry()
                it.run()
                "apples"
            }

        assertThat(result).isEqualTo("apples")
    }

    /**
     * RUN - similar to APPLY
     * - Access to "this"
     * - returns the result of the closure.
     * - used to run some code and then return different value.
     * - throw away the original instance.
     */
    @Test
    fun runTest() {
        var result = Car(0, "Toyota")
            .run {
                gas = 100
                carry()
                run()
                "tomatos"
            }

        println(result)

        assertThat(result).isEqualTo("tomatos")
    }

    /**
     * WITH
     * - access to this
     * - run a closure
     * - return different result
     */
    @Test
    fun withTest() {
        var result = with(Car(0, "Ford")) {
            gas = 100
            carry()
            run()
            "DONE"
        }

        println(result)
    }


    /**
     * Similar to try-with-resource. Use will safely close the closable.
     */
    @Test
    fun useTest() {
//        val input = Files.newInputStream(Paths.get(ClassLoader.getSystemResource("/test/test.csv").toURI()))
//        val byte = input.use {
//            it.read()
//        }
//
//        println(byte)
    }
}