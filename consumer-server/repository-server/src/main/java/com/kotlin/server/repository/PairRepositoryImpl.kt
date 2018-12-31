package com.kotlin.server.repository


import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.repository.PairsRepository
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.DbInterface
import com.kotlin.server.repository.mapper.MapperToPairStore
import com.kotlin.server.repository.mapper.MapperToPairSymbol

class PairRepositoryImpl(private val db: DbInterface,
                         val api: BxApi) : PairsRepository {

    private val mapperToPairSymbol = MapperToPairSymbol()
    private val mapperToPairStore = MapperToPairStore()

    override fun syncPairs(): List<PairSymbol> {
        api.getPairsInfo().pairInfoList.forEach {

            if (db.queryPairById(it.pairing_id) == null) {
                db.savePair(mapperToPairStore.transform(it))
            } else {
                val pairStore = db.queryPairById(it.pairing_id)
                pairStore.volume = it.volume_24_hours
                pairStore.rate = it.last_price
                db.savePair(pairStore)
            }

        }
        return getPairs()
    }

    override fun getPairs(): List<PairSymbol> =
            db.getPairs().map { mapperToPairSymbol.transform(it) }
}
