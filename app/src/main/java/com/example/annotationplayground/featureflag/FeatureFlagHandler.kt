package com.example.annotationplayground.featureflag

import kotlinx.coroutines.runBlocking
import java.lang.reflect.Proxy
import java.util.Locale


class FeatureFlagHandler(
    val firebaseRemoteConfigDataSource: FirebaseRemoteConfigDataSource,
    val growthBookDataSource: GrowthBookDataSource
) {
    fun <T> create(service: Class<T>): T {
        return Proxy.newProxyInstance(
            service.classLoader, arrayOf(service)
        ) { proxy, method, args ->
            /// Retrieving from the data source
            runBlocking {
                if (method.isAnnotationPresent(FirebaseRemoteConfig::class.java)) {
                    /// pdp_tantangan
                    val key = method.name.camelToSnakeCase()
                    val valueFromFirebase = firebaseRemoteConfigDataSource.featureFlagByKey(key)
                    if (valueFromFirebase == null) {
                        if (method.isAnnotationPresent(GrowthBook::class.java)) {
                            return@runBlocking growthBookDataSource.featureFlagByKey(key)
                        } else {
                            return@runBlocking false
                        }
                    }
                    return@runBlocking valueFromFirebase
                }
                return@runBlocking false
            }
        } as T
    }

    fun String.camelToSnakeCase(): String {
        val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()
        return camelRegex.replace(this) {
            "_${it.value}"
        }.lowercase(Locale.ROOT)
    }
}