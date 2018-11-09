package com.kotlin.client.repository

import com.kotlin.client.database.TradeDb
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades

interface GetTradesRepository {
    fun getTradesRemotely(id: Long): Trades

    fun getTradesPersisted(id: Long): Trades

    fun saveTrade(trade: TradeDb)

    fun getLastTradesById(id: Long): Trade?

    fun sync()
}