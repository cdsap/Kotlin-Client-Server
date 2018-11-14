package com.kotlin.server.domain

import com.kotlin.core.repository.SyncRepository
import com.kotlin.core.usecases.SyncPairs
import com.kotlin.server.repository.SyncPairsRepositoryImpl

class SyncPairsImpl(val syncPairsRepository: SyncRepository) : SyncPairs {
    override fun syncPairs() {
        syncPairsRepository.sync()
    }

}