package y.w.kotlin.study

import org.junit.Test

// Interface can have properties - placeholder without being materialized

interface Account {
    val id: String
}

class CreditCardAccount(override val id: String): Account

class CheckingAccount(val accountNumber: String): Account {
    override val id: String
        get() = accountNumber.substring(0..5) // a customer getter
}

class SavingAccount(val accountNumber: String): Account {
    override val id: String
        get() = TODO("Not yet implemented")

    var anotherOne: String = "unknown"
        set(value: String) {
            println("Setting value $value")
            field = value
        }

}

class TestThisCase {
    @Test
    fun t1() : Unit {
        val saving = SavingAccount("1234567")
        saving.anotherOne = "7777"

        println("accountNumber = ${saving.accountNumber} anotherOne=${saving.anotherOne}")
    }
}