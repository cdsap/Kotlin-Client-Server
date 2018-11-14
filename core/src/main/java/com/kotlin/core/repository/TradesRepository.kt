package com.kotlin.core.repository

import com.kotlin.core.entities.Trades

interface TradesRepository<T> {
    fun getTradesRemote(id: Long): Trades

    fun getTradesPersisted(id: Long): Trades

    fun save(trade: T)
}