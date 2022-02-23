package y.w.kotlin.study

import java.util.*

// Option 1
open class Super1(val id: String, val created: Date)

class Sub1(name: String, id: String, created: Date) : Super1(id, created)

class Sub2(var name: String, id: String, created: Date) : Super1(id, created)

// Option 2
abstract class Super2 {
    abstract var id: String
    abstract var created: Date
}

class Sub3(
    var name: String,
    override var id: String,
    override var created: Date
): Super2()

// Option 3
open class Super3(var id: String? = null, var created: Date? = Date())
class Sub4 constructor(var name: String, var id2: String?, var created2: Date?): Super3() {
    init {
        this.id = id2
        this.created = created2
    }

    companion object {
        fun copy(s: Sub4): Sub4 {
            return Sub4(s.name, s.id, s.created)
        }
    }
}