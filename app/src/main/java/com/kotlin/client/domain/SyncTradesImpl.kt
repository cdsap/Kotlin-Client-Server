package com.kotlin.client.domain

import com.kotlin.client.database.TradeDb
import com.kotlin.client.repository.GetTradesRepository
import com.kotlin.core.usecases.SyncTrades
import javax.inject.Inject

class SyncTradesImpl @Inject constructor(private val localRepository: GetTradesRepository,
                                         private val remoteRepository: GetTradesRepository)
    : SyncTrades {

    override fun syncTrades(id: Long) {
        val lastTrades = localRepository.getTrades(id).trades
        remoteRepository.getTrades(id).trades
                .filter { lastTrades.isNotEmpty() && it.trade_id > lastTrades[0].trade_id }
                .map { TradeDb(it.trade_id, it.rate, it.amount, it.trade_date, it.trade_type) }
                .map { localRepository.save(it) }
    }
}