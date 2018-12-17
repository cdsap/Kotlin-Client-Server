package com.kotlin.core.repository

import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades

interface TradesRepository {
    fun getTradesRemote(id: Long): Trades

    fun getTradesPersisted(id: Long): Trades

    fun save(trade: Trade)
}