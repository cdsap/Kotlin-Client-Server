package com.kotlin.server.service

import com.kotlin.core.usecases.SyncTrades
import javax.inject.Inject


class SyncTradesService @Inject constructor(private val syncTrades: SyncTrades) {

    fun sync(): Unit = syncTrades.syncTrades()
}