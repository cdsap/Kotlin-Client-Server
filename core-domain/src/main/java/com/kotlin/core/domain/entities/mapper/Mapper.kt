package com.kotlin.core.domain.entities.mapper

interface Mapper<T, R> {
    fun transform(origin: T): R
}
