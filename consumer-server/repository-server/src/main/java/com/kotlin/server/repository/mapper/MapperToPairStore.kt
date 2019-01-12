package com.kotlin.server.repository.mapper

import com.kotlin.core.domain.entities.mapper.Mapper
import com.kotlin.server.repository.api.entities.PairInfo
import com.kotlin.server.repository.database.PairStore

class MapperToPairStore : Mapper<PairInfo, PairStore> {

    override fun transform(origin: PairInfo): PairStore {
        return PairStore(
                id = origin.pairing_id,
                volume = origin.volume_24_hours,
                rate = origin.last_price,
                primaryPairId = origin.primary_currency,
                secondaryPairId = origin.secondary_currency)
    }
}
