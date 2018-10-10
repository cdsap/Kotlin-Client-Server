package com.kotlin.client.domain

import com.kotlin.client.database.TradeDb
import com.kotlin.client.repository.GetTradesRepository
import com.kotlin.core.usecases.SyncTrades
import javax.inject.Inject

class SyncTradesImpl @Inject constructor(private val repository: GetTradesRepository)
    : SyncTrades {

    override fun syncTrades(id: Long) {
        val getLastTradeById = repository.getLastTradesById(id)
        repository.getTrades(id).trades
                //   .filter { getLastTradeById.isNotEmpty() && it.trade_id > getLastTradeById[0].trade_id }
                .map { TradeDb(it.trade_id, it.rate, it.amount, it.trade_date, it.trade_type, 1L) }
                .map { repository.save(it) }
    }
}