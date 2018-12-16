package com.kotlin.server.repository.mapper

interface Mapper<T, R> {
    fun transform(origin: T): R
}