package com.kotlin.server.service

import com.kotlin.core.usecases.SyncPairs
import javax.inject.Inject


class SyncPairs @Inject constructor(private val syncPairs: SyncPairs) {

    fun sync(): Unit = syncPairs.syncPairs()
}