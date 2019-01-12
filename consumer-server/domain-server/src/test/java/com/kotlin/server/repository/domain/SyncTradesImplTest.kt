package com.kotlin.server.repository.domain;

import com.kotlin.core.domain.entities.PairSymbol
import com.kotlin.core.domain.entities.Trade
import com.kotlin.core.domain.entities.Trades
import com.kotlin.core.domain.entities.repository.PairsRepository
import com.kotlin.core.domain.entities.repository.TradesRepository
import com.nhaarman.mockitokotlin2.*
import io.kotlintest.specs.BehaviorSpec


class SyncTradesImplTest : BehaviorSpec({
    given("SyncTades Implementation") {

        `when`("There are no Pairs ") {
            val pairRepository = mock<PairsRepository> { }
            val tradesRepository = mock<TradesRepository> { }
            val syncTrades = SyncTradesImpl(tradesRepository, pairRepository)
            whenever(pairRepository.getPairs()).thenReturn(emptyList())
            syncTrades.syncTrades()
            then("I shouldn't fetch remote trades") {
                verify(tradesRepository, never()).getTradesRemote(any())
            }
        }
        `when`("There are Pairs") {
            val pairRepository = mock<PairsRepository> { }
            val tradesRepository = mock<TradesRepository> { }
            val syncTrades = SyncTradesImpl(tradesRepository, pairRepository)
            whenever(pairRepository.getPairs()).thenReturn(listOf(PairSymbol(1, "BTC", "EUR",
                    0.0, 0.0)))
            whenever(tradesRepository.getTradesRemote(any())).thenReturn(Trades(listOf(Trade())))
            syncTrades.syncTrades()
            then("I should fetch remote trades and save trades on DB") {
                verify(tradesRepository).getTradesRemote(any())
                verify(tradesRepository).save(argThat {
                    trade_id == 0L && pair == 1L

                })
            }
        }
    }
})