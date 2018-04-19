package com.kotlin.server.service

import com.kotlin.core.usecases.SyncTrades
import javax.inject.Inject


class SyncService @Inject constructor(private val syncTrades: SyncTrades) {

    fun sync(pair: String): Unit = syncTrades.syncTrades(pair.toLong())
}