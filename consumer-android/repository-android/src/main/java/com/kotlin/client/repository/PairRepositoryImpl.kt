package com.kotlin.client.repository

import com.kotlin.client.repository.database.DbInterface
import com.kotlin.client.repository.api.BxApi
import com.kotlin.client.repository.mapper.MapperToPairDb
import com.kotlin.client.repository.mapper.MapperToPairSymbol
import com.kotlin.client.repository.mapper.MapperToTradeDb
import com.kotlin.core.domain.entities.PairSymbol
import com.kotlin.core.domain.entities.repository.PairsRepository

class PairRepositoryImpl(val db: DbInterface,
                         val api: BxApi) : PairsRepository {
    private val mapperToPairDb = MapperToPairDb()
    private val mapperToPairSymbol = MapperToPairSymbol()
    private val mapperToTradeDb = MapperToTradeDb()

    override fun syncPairs(): List<PairSymbol> {
        api.syncTrades().forEach {

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