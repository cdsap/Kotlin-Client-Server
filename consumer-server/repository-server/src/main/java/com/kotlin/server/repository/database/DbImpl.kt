package com.kotlin.server.repository.database

import com.googlecode.objectify.Objectify
import com.kotlin.core.entities.Trade

class DbImpl(val db: Objectify) : DbInterface {
    override fun savePair(pairStore: PairStore) {
        db.savePair(pairStore)
    }

    override fun saveTrade(tradeStore: TradeStore) {
        db.saveTrade(tradeStore)
    }

    override fun getPairs(): List<PairStore> = db.queryPairs()


    override fun queryPairById(id: Long): PairStore = db.queryPairById(id).now()

    override fun queryTrades(id: Long): List<Trade> = db.queryTrades(id)

}