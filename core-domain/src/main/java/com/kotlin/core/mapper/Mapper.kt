package com.kotlin.core.mapper

interface Mapper<T, R> {
    fun transform(origin: T): R
}
