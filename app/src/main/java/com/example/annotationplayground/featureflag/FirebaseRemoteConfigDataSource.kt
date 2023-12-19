package com.example.annotationplayground.featureflag

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class FirebaseRemoteConfigDataSource {
    suspend fun featureFlagByKey(key: String): Boolean? {
        return runBlocking {
            delay(1000L)
            if (key == "pdp_tantangan") {
                return@runBlocking true
            } else {
                return@runBlocking null
            }
        }
    }
}