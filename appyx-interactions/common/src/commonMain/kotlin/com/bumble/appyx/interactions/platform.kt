package com.bumble.appyx.interactions

expect fun getPlatformName(): String

@Target(AnnotationTarget.CLASS)
expect annotation class Parcelize()

@Target(AnnotationTarget.TYPE)
expect annotation class RawValue()

expect interface Parcelable
