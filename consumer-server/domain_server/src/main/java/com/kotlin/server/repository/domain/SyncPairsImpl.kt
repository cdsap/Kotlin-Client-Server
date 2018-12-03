package com.kotlin.server.repository.domain

import com.kotlin.core.repository.SyncRepository
import com.kotlin.core.usecases.SyncPairs

class SyncPairsImpl(val syncPairsRepository: SyncRepository) : SyncPairs {
    override fun syncPairs() {
        syncPairsRepository.sync()
    }
}