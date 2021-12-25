package y.w.kotlin

// Object
object Person {
    val name = "Jack"
}

class Machine(val name: String) {
    companion object {
        fun newInstance(name: String): Machine {
            val instance = Machine(name)
            return instance
        }
    }
}

fun checkAddress() {
    // An anonymous object - an expression
    val addr = object {
        val street = "1234 Xyz St"
        val zip = "12345"
    }

    val machine = Machine.newInstance("tank")

    println(addr.street)
    println(Person.name)
    println(machine.name)
}

fun main() {
    checkAddress()
}
