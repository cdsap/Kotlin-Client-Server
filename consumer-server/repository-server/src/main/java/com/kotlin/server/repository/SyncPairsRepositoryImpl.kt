package com.kotlin.server.repository

import com.googlecode.objectify.Objectify
import com.kotlin.core.entities.Market
import com.kotlin.core.repository.SyncRepository
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.PairStore

class SyncPairsRepositoryImpl(private val db: Objectify,
                              private val api: BxApi) : SyncRepository {

    override fun sync(): List<Market> {
        // val pairs = db.load()
        //         .type(PairStore::class.java)
        //         .map { it.id }
        api.getPairsInfo().pairInfoList.forEach {
            System.out.println("***************")
            System.out.println("***************"+ db.load().type(PairStore::class.java).id(it.pairing_id))

                    //   if (db.load().type(PairStore::class.java).id(it.pairing_id) == null) {
                db.save().entity(PairStore(
                        id = it.pairing_id,
                        volume = it.volume_24_hours,
                        rate = it.last_price,
                        primaryPairId = it.primary_currency,
                        secondaryPairId = it.secondary_currency)
                )

//            } else {
//                val pairStore = db.load().type(PairStore::class.java)
//                        .id(it.pairing_id).safe()
//                pairStore.volume = it.volume_24_hours
//                pairStore.rate = it.last_price
//
//                db.save().entity(pairStore)
//            }

        }
        return mutableListOf()
    }
}