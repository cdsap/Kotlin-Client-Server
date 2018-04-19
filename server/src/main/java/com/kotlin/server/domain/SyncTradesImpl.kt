package com.kotlin.server.domain

import com.kotlin.core.usecases.SyncTrades
import com.kotlin.server.database.TradeStore
import com.kotlin.server.repository.GetTradesRepository
import javax.inject.Inject

class SyncTradesImpl @Inject constructor(private val localRepository: GetTradesRepository,
                                         private val remoteRepository: GetTradesRepository)
    : SyncTrades {
    override fun syncTrades(id: Long) {
        remoteRepository.getTrades(id).trades
                .map {
                    TradeStore(trade_type = it.trade_type,
                            trade_date = it.trade_date,
                            trade_id = it.trade_id,
                            rate = it.rate,
                            amount = it.amount)
                }
                .map { localRepository.save(it) }
    }

}