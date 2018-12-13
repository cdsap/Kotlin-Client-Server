package com.kotlin.core.repository

import com.kotlin.core.entities.Market

interface SyncRepository {
    fun sync(): List<Market>
}