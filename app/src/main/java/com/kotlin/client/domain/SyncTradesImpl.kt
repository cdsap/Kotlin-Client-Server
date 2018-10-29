package com.kotlin.client.domain

import com.kotlin.client.repository.GetTradesRepository
import com.kotlin.core.usecases.SyncTrades
import javax.inject.Inject

class SyncTradesImpl @Inject constructor(private val repository: GetTradesRepository)
    : SyncTrades {
    override fun syncTrades() {
        repository.sync()
    }
}