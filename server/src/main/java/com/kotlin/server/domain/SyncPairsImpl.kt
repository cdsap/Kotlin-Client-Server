package com.kotlin.server.domain

import com.kotlin.core.usecases.SyncPairs
import com.kotlin.server.repository.SyncPairsRepository

class SyncPairsImpl(val syncPairsRepository: SyncPairsRepository) : SyncPairs {
    override fun syncPairs() {
        syncPairsRepository.sync()
    }

}