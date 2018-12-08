package com.kotlin.core.repository

interface RemoteSource<T> {
    fun sync(): List<T>
}