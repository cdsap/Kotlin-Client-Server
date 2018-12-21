package com.kotlin.client.repository

import com.kotlin.client.repository.database.DbInterface
import com.kotlin.client.repository.database.PairDb
import com.kotlin.client.repository.database.TradeDb
import com.kotlin.client.repository.api.BxApi
import com.kotlin.client.repository.mapper.MapperToPairDb
import com.kotlin.client.repository.mapper.MapperToPairSymbol
import com.kotlin.client.repository.mapper.MapperToTradeDb
import com.kotlin.core.entities.Market
import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.entities.Trade
import com.kotlin.core.repository.PairsRepository

class PairRepositoryImpl(val db: DbInterface,
                         val api: BxApi) : PairsRepository {
    private val mapperToPairDb = MapperToPairDb()
    private val mapperToPairSymbol = MapperToPairSymbol()
    private val mapperToTradeDb = MapperToTradeDb()

    override fun syncPairs(): List<PairSymbol> {
        api.syncTrades().forEach {
            // TODO
            // IS POSSIBLE TO USE INSERT OR UPDATE
            if (db.getPairs(it.pairSymbol.id) == null) {
                db.insertPair(mapperToPairDb.transform(it))
            } else {
                db.updatePair(mapperToPairDb.transform(it))
            }
            it.trades.trades.map { trade ->
                db.insertTrade(mapperToTradeDb.transform(trade))
            }
        }
        return getPairs()
    }

    override fun getPairs(): List<PairSymbol> {
        return db.getPairs().map {
            mapperToPairSymbol.transform(it)
        }
    }
}