package y.w.kotlin.study.annonations

object AnnotationValidator {
    fun check(item: Any): Boolean {
        val fields = item::class.java.declaredFields
        for (field in fields) {
            field.isAccessible = true
            for (annotation in field.annotations) {
                val value = field.get(item)
                if (field.isAnnotationPresent(ColumnName::class.java)) {
                    val name = field.getAnnotation(ColumnName::class.java).name
                    println("ColumnName ($name, $value)")
                }
                if (field.isAnnotationPresent(AllowedValues::class.java)) {
                    val allowedTypes = field.getAnnotation(AllowedValues::class.java)?.types
                    allowedTypes?.let {
                        println("Allowed types: ${it.joinToString(", ")}, value=$value")
                    }
                }
            }
        }
        return true
    }
}
