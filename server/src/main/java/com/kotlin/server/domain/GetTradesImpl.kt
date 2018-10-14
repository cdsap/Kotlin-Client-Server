package com.kotlin.server.domain

import com.kotlin.core.entities.Trades
import com.kotlin.core.usecases.GetTrades
import com.kotlin.server.database.TradeStore
import com.kotlin.server.repository.GetTradesRepository
import javax.inject.Inject

class GetTradesImpl @Inject constructor(
        private val repository: GetTradesRepository) : GetTrades {

    override fun getTrades(id: Long): Trades {
        val trades = repository.getTrades(id)
        if (trades.trades.isEmpty()) {
            repository.getTrades(1).trades
                    .map {
                        TradeStore(trade_type = it.trade_type,
                                trade_date = it.trade_date,
                                trade_id = it.trade_id,
                                rate = it.rate,
                                amount = it.amount)
                    }
                    .map { repository.save(it) }

        }
        return repository.getTrades(1)
    }
}