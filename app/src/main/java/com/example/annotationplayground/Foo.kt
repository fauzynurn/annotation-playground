package com.example.annotationplayground

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FIELD
)
//@Repeatable
@Retention(AnnotationRetention.RUNTIME)
annotation class Foo(val value: String)

@Foo("class")
class SomeClass {
    @Foo("property-1")
    lateinit var someValue1: String

    @Foo("property-2")
    lateinit var someValue2: String

    @Foo("function")
    fun someFunction(
        @Foo("value-parameter")
        param: Int
    ): Int {
        return param + 2
    }
}

fun main() {
    SomeClass::class.java.let {
        if (it.isAnnotationPresent(Foo::class.java)) {
            val value = it.getAnnotation(Foo::class.java)?.value
            print("someClass class has annotation Foo with value $value\n\n")
        }
    }

    SomeClass::class.java.declaredFields.let {
        it.forEach { field ->
            if (field.isAnnotationPresent(Foo::class.java)) {
                print("field ${field.name} has annotation Foo with value ${field.getAnnotation(Foo::class.java)!!.value}\n")
            }
        }
        print("\n")
    }

    SomeClass::class.java.declaredMethods.let {
        it.forEach { method ->
            print("list of parameter of method ${method.name}:\n")
            method.parameters.forEach { parameter ->
                if (parameter.isAnnotationPresent(Foo::class.java)) {
                    print("- parameterName: ${parameter.name}, with annotation Foo with value ${
                        parameter.getAnnotation(Foo::class.java)?.value
                    }\n")
                }
            }
        }
    }

}