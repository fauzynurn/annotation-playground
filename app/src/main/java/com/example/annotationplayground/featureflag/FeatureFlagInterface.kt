package com.example.annotationplayground.featureflag

interface FeatureFlagInterface {
    @GrowthBook
    suspend fun cancelOrderEnabled(): Boolean

    @FirebaseRemoteConfig
    @GrowthBook
    suspend fun pinAuthEnabled(): Boolean

    @FirebaseRemoteConfig
    suspend fun pdpTantangan(): Boolean
}