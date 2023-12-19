package com.example.annotationplayground.featureflag

suspend fun main() {
    val featureFlagInstance = FeatureFlagHandler(
        firebaseRemoteConfigDataSource = FirebaseRemoteConfigDataSource(),
        growthBookDataSource = GrowthBookDataSource()
    ).create(FeatureFlagInterface::class.java)
    print("${featureFlagInstance.pdpTantangan()} \n")
    print("${featureFlagInstance.pinAuthEnabled()} \n")
    print("${featureFlagInstance.cancelOrderEnabled()} \n")
}