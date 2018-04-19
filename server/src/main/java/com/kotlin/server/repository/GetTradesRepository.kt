package com.kotlin.server.repository

import com.kotlin.core.entities.Trades
import com.kotlin.server.database.TradeStore

interface GetTradesRepository {

    fun save(trade: TradeStore)

    fun getTrades(id: Long): Trades
}