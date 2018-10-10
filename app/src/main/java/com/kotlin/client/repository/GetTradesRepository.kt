package com.kotlin.client.repository

import com.kotlin.client.database.TradeDb
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades

interface GetTradesRepository {
    fun getTradesRemotely(id: Long): Trades

    fun getTradesPersisted(id: Long): Trades

    fun save(trade: TradeDb)

    fun getTrades(id: Long): Trades

    fun getLastTradesById(id: Long): Trade
}