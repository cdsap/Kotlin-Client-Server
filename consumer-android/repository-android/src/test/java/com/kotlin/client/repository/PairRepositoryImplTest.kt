package com.kotlin.client.repository

import com.kotlin.client.repository.api.BxApi
import com.kotlin.client.repository.database.DbInterface
import com.kotlin.client.repository.database.PairDb
import com.kotlin.core.domain.entities.Market
import com.kotlin.core.domain.entities.PairSymbol
import com.kotlin.core.domain.entities.Trade
import com.kotlin.core.domain.entities.Trades
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotlintest.specs.BehaviorSpec


class PairRepositoryImplTest : BehaviorSpec({
    given("Pair Repository Implementation") {
        `when`("Get pairs") {
            val db = mock<DbInterface> {}
            val api = mock<BxApi> {}
            val pairRepository = PairRepositoryImpl(db, api)
            whenever(db.getPairs()).thenReturn(listOf(PairDb(id = 1L,
                    lastPrice = 0.1,
                    volume = 0.1,
                    primaryPairId = "BTC",
                    secondaryPairId = "OMG")))

            then("a list of Domain Pairs is returned ") {
                val pairs = pairRepository.getPairs()
                assert(pairs.get(0).id == 1L)
                verify(db).getPairs()
            }
        }
        `when`("Syncing pairs") {
            val db = mock<DbInterface> {}
            val api = mock<BxApi> {}
            val pairRepository = PairRepositoryImpl(db, api)
            whenever(db.getPairs(1L)).thenReturn(null)
            whenever(api.syncTrades()).thenReturn(listOf(Market(PairSymbol(id = 1L,
                    rate = 1.0,
                    volume = 1.0,
                    primarySymbol = "BTC",
                    secondarySymbol = "OMG"), Trades(listOf(Trade(trade_id = 1L,
                    rate = 1.3,
                    trade_type = "sell",
                    trade_date = "",
                    pair = 1L,
                    amount = 2.0))))))


            then("we shouoild inserta the pari and later the trades ") {
                pairRepository.syncPairs()
                verify(db).insertPair(argThat {
                    id == 1L
                            && primaryPairId == "BTC"
                            && secondaryPairId == "OMG"
                            && volume == 1.0
                            && lastPrice == 1.0
                })
                verify(db).insertTrade(argThat {
                    this.trade_id == 1L
                            && trade_type == "sell"
                            && rate == 1.3
                            && pair == 1L
                })

            }

        }
    }
})