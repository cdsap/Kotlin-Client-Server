package com.kotlin.server.repository

import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.DbInterface
import com.kotlin.server.repository.database.PairStore
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotlintest.specs.BehaviorSpec


class SyncPairsRepositoryTest : BehaviorSpec({
    given("SyncPairRepository Implementation") {

        `when`("GET pAI pairs") {
            val db = mock<DbInterface> {}
            val api = mock<BxApi> {}
            val syncRepository = SyncPairsRepositoryImpl(db, api)
            whenever(db.getPairs()).thenReturn(listOf(PairStore(id = 1L,
                    rate = 0.1,
                    volume = 0.1,
                    primaryPairId = "BTC",
                    secondaryPairId = "OMG")))

            then("a list of Domain Pairs is returned ") {
                val markets = syncRepository.get()
                verify(db).getPairs()
                assert(markets[0].pairSymbol.id == 1L)
                assert(markets[0].pairSymbol.rate == 0.1)
                assert(markets[0].pairSymbol.primarySymbol == "BTC")
                assert(markets[0].pairSymbol.secondarySymbol == "OMG")

            }
        }
    }
})
