package com.kotlin.server.repository

import com.googlecode.objectify.Objectify
import com.kotlin.core.repository.SyncRepository
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.PairStore

class SyncPairsRepositoryImpl(private val db: Objectify,
                              private val api: BxApi) : SyncRepository {

    override fun sync() {
        println("kckckckkckckck $db")
        val pairs = db.load()
                .type(PairStore::class.java)
                .map { it.id }
System.out.println("11111")
        api.getPairsInfo().pairInfoList.forEach {
            if (pairs.contains(it.pairing_id)) {
                val a = db.load().type(PairStore::class.java)
                        .id(it.pairing_id).safe()
                a.volume = it.volume_24_hours
                a.rate = it.last_price
                try {
                    db.save().entity(a)
                } catch (e: Exception){
                    System.out.println(e.message)
                }
            }

        }
    }
}