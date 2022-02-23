package y.w.kotlin.study

// No constructor provided.
// Kotlin will create one as default constructor
class C1(var id: String, var name: String)

// Primary constructor
class C2 constructor(var id: String, var name: String)

class C3(var id: String, var name: String)

// C3 is equivalent to the following
class C3_v2 constructor(_id: String, _name: String) {
    val id: String
    val name: String

    init {
        this.id = _id
        this.name = _name
    }
}

class C3_v3(_id: String, _name: String) { // no var or val - just a parameter to constructor
    val id: String = _id
    val name: String = _name
}

// Initializing properties
class C4(var id: String, var name: String) {
    var newId: String = id
    var newName: String = name
}

class C5_Using_Init(var id: String, var name: String) {
    var newId: String
    var newName: String

    init {
        this.newId = id
        this.newName = name
    }
}

// Secondary constructor
class C6(var id: String?, var name: String?) {
    constructor(n: String): this("1", n)
}

open class Super10 {
    constructor(name: String) {}
    constructor(name: String, email: String)
}

class SubC: Super10 {
    constructor(name: String): super(name)
    constructor(name: String, email: String): super(name, email)
}

class SubD: Super10 {
    private var email: String? = null

    constructor(name: String): super(name)
    constructor(name: String, email: String): super(name) {
        this.email = email
    }
}

// Instances:
val c61 = C6("2", "Someone") // Use primary constructor
val c62 = C6("Someone") // Use secondary constructor

//
open class Bar(val value: String);

class Primary(value: String, other: String) : Bar(value)

class Secondary : Bar {
    constructor(value: String, other: String) : super(value)
}