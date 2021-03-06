package com.kotlin.server.repository.mapper

import com.kotlin.core.domain.entities.PairSymbol
import com.kotlin.core.domain.entities.mapper.Mapper
import com.kotlin.server.repository.database.PairStore

class MapperToPairSymbol : Mapper<PairStore, PairSymbol> {
    override fun transform(origin: PairStore): PairSymbol {
        return PairSymbol(id = origin.id,
                primarySymbol = origin.primaryPairId,
                secondarySymbol = origin.secondaryPairId,
                volume = origin.volume,
                rate = origin.rate)
    }
}
