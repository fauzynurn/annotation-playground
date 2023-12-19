package com.example.annotationplayground.featureflag

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class GrowthBookDataSource {
    suspend fun featureFlagByKey(key: String): Boolean {
        return runBlocking {
            delay(1000L)
            return@runBlocking key == "pin_auth_enabled" || key == "cancel_order_enabled"
        }
    }
}