package com.kotlin.server.repository


import com.googlecode.objectify.Objectify
import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.repository.PairsRepository
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.PairStore

class PairRepositoryImpl(val db: Objectify,
                         val api: BxApi) : PairsRepository {


    override fun syncPairs(): List<PairSymbol> {
        api.getPairsInfo().pairInfoList.forEach {

            if (db.load().type(PairStore::class.java).id(it.pairing_id).now() == null) {
                db.save().entity(PairStore(
                        id = it.pairing_id,
                        volume = it.volume_24_hours,
                        rate = it.last_price,
                        primaryPairId = it.primary_currency,
                        secondaryPairId = it.secondary_currency)
                )

            } else {
                val pairStore = db.load().type(PairStore::class.java)
                        .id(it.pairing_id).safe()
                pairStore.volume = it.volume_24_hours
                pairStore.rate = it.last_price

                db.save().entity(pairStore)
            }

        }
        return mutableListOf()
    }

    override fun getPairs(): List<PairSymbol> {
        return db.load().type(PairStore::class.java)
                .list()
                .map {
                    PairSymbol(id = it.id,
                            primarySymbol = it.primaryPairId,
                            secondarySymbol = it.secondaryPairId,
                            rate = it.rate,
                            volume = it.volume)
                }
    }
}