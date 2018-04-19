package com.kotlin.client.repository

import com.kotlin.client.database.TradeDb
import com.kotlin.core.entities.Trades

interface GetTradesRepository {
    fun save(trade: TradeDb)

    fun getTrades(id: Long): Trades
}