package com.kotlin.client.domain

import com.kotlin.client.database.DbInterface
import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.usecases.GetPairs

class GetPairsImpl(val db: DbInterface) : GetPairs {


    override fun get(): List<PairSymbol> {
        return db.getPairs().map {
            PairSymbol(it.id,
                    it.primaryPairId,
                    it.secondaryPairId,
                    0.0)
        }
    }

}