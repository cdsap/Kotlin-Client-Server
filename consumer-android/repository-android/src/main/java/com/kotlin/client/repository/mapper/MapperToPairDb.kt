package com.kotlin.client.repository.mapper

import com.kotlin.client.repository.database.PairDb
import com.kotlin.core.entities.Market
import com.kotlin.core.mapper.Mapper

class MapperToPairDb : Mapper<Market, PairDb> {
    override fun transform(origin: Market): PairDb {
        return PairDb(
                id = origin.pairSymbol.id,
                volume = origin.pairSymbol.volume,
                primaryPairId = origin.pairSymbol.primarySymbol,
                secondaryPairId = origin.pairSymbol.secondarySymbol,
                lastPrice = origin.pairSymbol.rate
        )
    }

}