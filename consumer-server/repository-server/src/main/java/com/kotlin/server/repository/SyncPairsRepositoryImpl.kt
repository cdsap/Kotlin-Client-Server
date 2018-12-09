package com.kotlin.server.repository

import com.googlecode.objectify.Objectify
import com.kotlin.core.repository.SyncRepository
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.PairStore

class SyncPairsRepositoryImpl(private val db: Objectify,
                              private val api: BxApi) : SyncRepository {

    override fun sync() {
        val pairs = db.load()
                .type(PairStore::class.java)
                .map { it.id }
        api.getPairsInfo().pairInfoList.forEach {
            if (pairs.contains(it.pairing_id)) {
                val pairStore = db.load().type(PairStore::class.java)
                        .id(it.pairing_id).safe()
                pairStore.volume = it.volume_24_hours
                pairStore.rate = it.last_price
                db.save().entity(pairStore)
            }

        }
    }
}