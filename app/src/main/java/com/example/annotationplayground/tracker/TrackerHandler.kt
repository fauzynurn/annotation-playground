package com.example.annotationplayground.tracker

import java.lang.reflect.Proxy
import java.util.Locale

class TrackerHandler {
    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(
            service.classLoader, arrayOf(service)
        ) { proxy, method, args ->

            val parameterWithUnderscore = method.parameters.toString()
            print(
                "Called ${method.toGenericString()} with params: $parameterWithUnderscore with annotation ${
                    method.getAnnotation(
                        TrackerName::class.java
                    )?.name
                }"
            )
            null
        } as T
    }

    fun String.camelToSnakeCase(): String {
        val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()
        return camelRegex.replace(this) {
            "_${it.value}"
        }.lowercase(Locale.ROOT)
    }
}