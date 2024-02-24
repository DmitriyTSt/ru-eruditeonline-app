package ru.eruditeonline.core.domain.ext

fun Int?.orDefault(default: Int = 0) = this ?: default
fun Long?.orDefault(default: Long = 0) = this ?: default
fun Float?.orDefault(default: Float = 0f) = this ?: default
fun Double?.orDefault(default: Double = 0.0) = this ?: default
fun Boolean?.orDefault(default: Boolean = false) = this ?: default
