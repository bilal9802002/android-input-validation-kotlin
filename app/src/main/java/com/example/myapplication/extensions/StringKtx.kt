package com.example.myapplication.extensions

private val charRange = CharRange('0', '9')

fun String.doubleOrNull(): Double? {
    return if (last() in charRange) {
        toDoubleOrNull()
    } else null
}