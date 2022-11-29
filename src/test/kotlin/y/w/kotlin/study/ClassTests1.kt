package y.w.kotlin.study

import org.junit.jupiter.api.Test

class AClass(val v:String, var va: String, vs: String)
//               |         |               |_no val and var: a parameter passed to constructor.
//               |         |                 init{} can access it for initialization.
//               |_________|__var and val declare properties inside the class.

// Classes are by default public.
// Compiler will generate a default constructor
class People
class Person(val age: Int, val name: String)

// Define a constructor
class Animal constructor(val name: String) {

    fun eat(food: String): Unit {
        println("Yes, $name's here. I'm eating $food")
    } // A function
}

// Initializing
class User(val name: String, val phone: String) {
    init { // Doing some initialization and validation...part of primary constructor
        require(phone.length >= 7)
    }
}

interface Clickable {
    fun click() = println("Clicked by Clickable")
}

class Button: Clickable {
    override fun click() = println("Clicked by Button")
}

open class Radio: Clickable // Must be "open" to be extended.

class CheckButton: Radio()  // Must call the constructor to initialize.

// Base class, regular or abstract
open abstract class Engine(val type: String) {
    abstract fun drive()
}
class TruckEngine: Engine("truck") {
    override fun drive() {
        TODO("Not yet implemented")
    }
}
class CarEngine: Engine("car") {
    override fun drive() {
        TODO("Not yet implemented")
    }
}

class ClassTests {
    @Test
    fun t1() {
        Animal("cat").eat("fish")

        User("user", "1231231")

        Button().click()
        Radio().click()

        println(CarEngine().type)
        println(TruckEngine().type)
    }
}

