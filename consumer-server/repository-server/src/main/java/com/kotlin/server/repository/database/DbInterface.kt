package com.kotlin.server.repository.database

import com.kotlin.core.entities.Trade

interface DbInterface {

    fun getPairs(): List<PairStore>

    fun queryPairById(id: Long): PairStore

    fun queryTrades(id: Long): List<Trade>

    fun savePair(pairStore: PairStore)

    fun saveTrade(tradeStore: TradeStore)

}