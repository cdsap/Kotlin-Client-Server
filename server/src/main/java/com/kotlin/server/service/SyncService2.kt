package com.kotlin.server.service

import com.kotlin.core.usecases.SyncPairs
import com.kotlin.core.usecases.SyncTrades
import javax.inject.Inject


class SyncService2 @Inject constructor(private val syncPairs: SyncPairs) {

    fun sync(): Unit = syncPairs.syncPairs()
}