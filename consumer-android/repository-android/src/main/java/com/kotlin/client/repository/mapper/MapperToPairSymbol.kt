package com.kotlin.client.repository.mapper

import com.kotlin.client.repository.database.PairDb
import com.kotlin.core.domain.entities.PairSymbol
import com.kotlin.core.domain.entities.mapper.Mapper

class MapperToPairSymbol : Mapper<PairDb, PairSymbol> {
    override fun transform(origin: PairDb): PairSymbol {
        return PairSymbol(id = origin.id,
                primarySymbol = origin.primaryPairId,
                secondarySymbol = origin.secondaryPairId,
                volume = origin.volume,
                rate = origin.lastPrice)
    }

}