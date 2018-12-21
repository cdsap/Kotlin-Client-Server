package com.kotlin.server.repository

import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.api.entities.PairInfo
import com.kotlin.server.repository.api.entities.PairsInfo
import com.kotlin.server.repository.database.DbInterface
import com.kotlin.server.repository.database.PairStore
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotlintest.specs.BehaviorSpec

class PairRepositoryImplTest : BehaviorSpec({
    given("PairRepiository Implementation") {

        `when`("Retrieve pairs") {
            val db = mock<DbInterface> {}
            val api = mock<BxApi> {}
            val pairRepository = PairRepositoryImpl(db, api)
            whenever(db.getPairs()).thenReturn(listOf(PairStore(id = 1L,
                    rate = 0.1,
                    volume = 0.1,
                    primaryPairId = "BTC",
                    secondaryPairId = "OMG")))

            then("a list of Domain Pairs is returned ") {
                val pairs = pairRepository.getPairs()
                assert(pairs.get(0).id == 1L)
                //         verify(pairRepository).syncPairs()
            }
        }
        `when`("Syncing pairs") {
            val db = mock<DbInterface> {}
            val api = mock<BxApi> {}
            val pairRepository = PairRepositoryImpl(db, api)
            whenever(db.queryPairById(1L)).thenReturn(null)
            whenever(api.getPairsInfo()).thenReturn(PairsInfo(listOf(PairInfo(
                    pairing_id = 1L,
                    last_price = 0.1,
                    volume_24_hours = 12.0,
                    primary_currency = "BTC",
                    secondary_currency = "OMG"))))
            then("a list of Dosasasasddsmain Pairs is returned ") {
                pairRepository.syncPairs()
                verify(db).savePair(argThat {
                    this.id == 1L
                            && this.primaryPairId == "BTC"
                            && this.secondaryPairId == "OMG"
                            && this.volume == 12.0
                            && this.rate == 0.1
                })

            }

        }
        `when`("Synceeeeing pairs") {
            val db = mock<DbInterface> {}
            val api = mock<BxApi> {}
            val pairRepository = PairRepositoryImpl(db, api)
            whenever(db.queryPairById(1L)).thenReturn(PairStore(
                    id = 1L,
                    primaryPairId = "BTC",
                    secondaryPairId = "OMG",
                    rate = 0.1,
                    volume = 0.1
            ))
            whenever(api.getPairsInfo()).thenReturn(PairsInfo(listOf(PairInfo(
                    pairing_id = 1L,
                    last_price = 1.0,
                    volume_24_hours = 12.0,
                    primary_currency = "BTC",
                    secondary_currency = "OMG"))))
            then("a list of Dosasasdsdsdasddsmain Pairs is returned ") {
                pairRepository.syncPairs()
                verify(db).savePair(argThat {
                    this.id == 1L
                            && this.primaryPairId == "BTC"
                            && this.secondaryPairId == "OMG"
                            && this.volume == 12.0
                            && this.rate == 1.0
                })

            }

        }

    }
})
