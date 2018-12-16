package com.kotlin.server.repository

import com.googlecode.objectify.Objectify
import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.SyncRepository
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.PairStore
import com.kotlin.server.repository.database.queryTrades
import com.kotlin.server.repository.mapper.MapperToPairStore
import com.kotlin.server.repository.mapper.MapperToPairSymbol

class SyncPairsRepositoryImpl(private val db: Objectify,
                              private val api: BxApi) : SyncRepository {
    private val mapperToPairStore = MapperToPairStore()
    private val mapperToPairSymbol = MapperToPairSymbol()

    override fun get(): List<Market> {
        val markets = mutableListOf<Market>()
        db.load().type(PairStore::class.java).forEach {
            markets.add(Market(mapperToPairSymbol.transform(it), Trades(db.queryTrades(it.id))))
        }
        return markets
    }


    override fun sync() {
        api.getPairsInfo().pairInfoList.forEach {
            if (db.load().type(PairStore::class.java).id(it.pairing_id).now() == null) {
                db.save().entity(mapperToPairStore.transform(it))
            } else {
                val pairStore = db.load().type(PairStore::class.java)
                        .id(it.pairing_id).safe()
                pairStore.volume = it.volume_24_hours
                pairStore.rate = it.last_price
                db.save().entity(pairStore)
            }
        }

    }

}