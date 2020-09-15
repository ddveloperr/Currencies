package com.example.common.ext

fun <T : Any> Collection<T?>.flattenAndFilterNull(): List<T> {
    return this.flatMap {
        @Suppress("UNCHECKED_CAST")
        if (it is Iterable<*>) it as Iterable<T?> else listOf(it)
    }.mapNotNull { it }
}