package com.kotlin.core.repository

interface LocalSource<in T> {
    fun update(entity: T)

    fun save(entity: T)
}