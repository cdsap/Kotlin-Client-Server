package com.kotlin.core.domain.entities.repository

import com.kotlin.core.domain.entities.Trade
import com.kotlin.core.domain.entities.Trades

interface TradesRepository {
    fun getTradesRemote(id: Long): Trades

    fun getTradesPersisted(id: Long): Trades

    fun save(trade: Trade)
}