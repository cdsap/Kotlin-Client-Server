package com.kotlin.server.domain

import com.kotlin.core.entities.Trades
import com.kotlin.core.usecases.GetTrades
import com.kotlin.server.database.TradeStore
import com.kotlin.server.repository.GetTradesRepository
import javax.inject.Inject

class GetTradesImpl @Inject constructor(
        private val repository: GetTradesRepository) : GetTrades {

    override fun getTrades(id: Long): Trades {
        val trades = repository.getTradesPersisted(id)

        return trades
    }
}