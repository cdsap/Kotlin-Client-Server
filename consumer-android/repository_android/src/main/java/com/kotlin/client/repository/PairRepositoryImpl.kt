package com.kotlin.client.repository

import com.kotlin.client.database.DbInterface
import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.repository.PairsRepository

class PairRepositoryImpl(val db: DbInterface) : PairsRepository {
    override fun getPairs(): List<PairSymbol> =
            db.getPairs().map {
                PairSymbol(id = it.id,
                        primarySymbol = it.primaryPairId,
                        secondarySymbol = it.secondaryPairId,
                        volume = it.volume,
                        rate = it.lastPrice)
            }
}