package com.example.annotationplayground.tracker

@Target(AnnotationTarget.FUNCTION)
annotation class TrackerName(val name: String)
