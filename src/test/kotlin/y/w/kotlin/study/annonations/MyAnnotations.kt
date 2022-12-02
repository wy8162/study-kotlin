package y.w.kotlin.study.annonations

@Target(AnnotationTarget.FIELD)
annotation class ColumnName(val name: String)

@Target(AnnotationTarget.FIELD)
annotation class AllowedValues(val types: Array<String>)
