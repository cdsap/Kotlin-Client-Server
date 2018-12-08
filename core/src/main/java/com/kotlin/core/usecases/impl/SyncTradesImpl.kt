package com.kotlin.core.usecases.impl

import com.kotlin.core.repository.SyncRepository
import com.kotlin.core.usecases.SyncTrades
import javax.inject.Inject

class SyncTradesImpl @Inject constructor(private val repository: SyncRepository)
    : SyncTrades {
    override fun syncTrades() {
        repository.sync()
    }
}