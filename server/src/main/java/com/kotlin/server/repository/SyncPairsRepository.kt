package com.kotlin.server.repository

import com.googlecode.objectify.Objectify
import com.kotlin.server.api.BxApi
import com.kotlin.server.database.PairStore

class SyncPairsRepository(private val db: Objectify,
                          private val api: BxApi) {

    fun sync() {
        val pairs = db.load()
                .type(PairStore::class.java)
                .map { it.id }

        api.getPairsInfo().pairInfoList.forEach {
            if (pairs.contains(it.pairing_id)) {
                val a = db.load().type(PairStore::class.java)
                        .id(it.pairing_id).safe()
                a.volume = it.volume_24_hours
                a.rate = it.last_price
                db.save().entity(a)
            }

        }
    }
}