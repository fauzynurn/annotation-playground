package com.example.annotationplayground.tracker

interface TrackerInterface {
    @TrackerName("evm_tracker_view")
    fun viewProduct(
        productId: String,
        productName: String,
    )
}