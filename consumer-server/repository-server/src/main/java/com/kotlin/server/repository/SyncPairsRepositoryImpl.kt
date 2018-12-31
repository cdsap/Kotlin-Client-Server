package com.kotlin.server.repository

import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.SyncRepository
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.*
import com.kotlin.server.repository.mapper.MapperToPairStore
import com.kotlin.server.repository.mapper.MapperToPairSymbol

class SyncPairsRepositoryImpl(private val db: DbInterface,
                              private val api: BxApi) : SyncRepository {
    private val mapperToPairStore = MapperToPairStore()
    private val mapperToPairSymbol = MapperToPairSymbol()

    override fun get(): List<Market> =
            db.getPairs()
                    .map {
                        Market(mapperToPairSymbol.transform(it),
                                Trades(db.queryTrades(it.id)))
                    }


    override fun sync() {
        api.getPairsInfo().pairInfoList.forEach {
            if (db.queryPairById(it.pairing_id) == null) {
                db.savePair(mapperToPairStore.transform(it))
            } else {
                val pairStore = db.queryPairById(it.pairing_id)
                pairStore.volume = it.volume_24_hours
                pairStore.rate = it.last_price
                db.savePair(pairStore)
            }
        }
    }
}
