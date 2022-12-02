package y.w.kotlin.study.annonations

import org.junit.jupiter.api.Test

class MyAnnotationsTest {
    @Test
    fun testAnnotations1() {
        val p = Person(firstName = "fName", lastName = "lName")
        AnnotationValidator.check(p)
    }
}

data class Person(
    @ColumnName("first_name")
    @AllowedValues(["Jack"])
    val firstName: String,
    @ColumnName("last_name")
    @AllowedValues(["x", "y"])
    val lastName: String
)
