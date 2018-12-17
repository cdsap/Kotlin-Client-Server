package com.kotlin.server.repository


import com.googlecode.objectify.Objectify
import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.repository.PairsRepository
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.PairStore
import com.kotlin.server.repository.database.queryPairById
import com.kotlin.server.repository.database.queryPairs
import com.kotlin.server.repository.mapper.MapperToPairStore
import com.kotlin.server.repository.mapper.MapperToPairSymbol

class PairRepositoryImpl(private val db: Objectify,
                         val api: BxApi) : PairsRepository {

    private val mapperToPairSymbol = MapperToPairSymbol()
    private val mapperToPairStore = MapperToPairStore()

    override fun syncPairs(): List<PairSymbol> {
        api.getPairsInfo().pairInfoList.forEach {

            if (db.queryPairById(it.pairing_id).now() == null) {
                db.save().entity(mapperToPairStore.transform(it))
            } else {
                val pairStore = db.load().type(PairStore::class.java)
                        .id(it.pairing_id).safe()
                pairStore.volume = it.volume_24_hours
                pairStore.rate = it.last_price
                db.save().entity(pairStore)
            }

        }
        return getPairs()
    }

    override fun getPairs(): List<PairSymbol> = db.queryPairs()
            .map { mapperToPairSymbol.transform(it) }
}
