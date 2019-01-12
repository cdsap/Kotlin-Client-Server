package com.kotlin.core.domain.entities.repository

import com.kotlin.core.domain.entities.Market

interface SyncRepository {
    fun sync()

    fun get(): List<Market>
}