package com.kotlin.server.repository


import com.googlecode.objectify.Objectify
import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.repository.PairsRepository
import com.kotlin.server.repository.database.PairStore

class PairRepositoryImpl(val db: Objectify) : PairsRepository {
    override fun getPairs(): List<PairSymbol> {
        return db.load().type(PairStore::class.java)
                .list()
                .map {
                    PairSymbol(id = it.id,
                            primarySymbol = it.primaryPairId.key.name,
                            secondarySymbol = it.secondaryPairId.key.name,
                            rate = it.rate,
                            volume = it.volume)
                }
    }
}