package com.kotlin.server.repository

import com.kotlin.core.domain.entities.Trade
import com.kotlin.core.domain.entities.Trades
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.DbInterface
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotlintest.specs.BehaviorSpec

class GetTradesRepositoryTest : BehaviorSpec({
    given("GetTradesRepository Implementation") {

        `when`("Trades are coming from Remote Source") {
            val db = mock<DbInterface> {}
            val api = mock<BxApi> {}
            val getTradesRepository = GetTradesRepositoryImpl(db, api)
            whenever(api.getTrades(1L)).thenReturn(Trades(listOf(Trade(
                    rate = 1.0,
                    amount = 2.0,
                    pair = 1L,
                    trade_date = "",
                    trade_id = 10L,
                    trade_type = "sell"
            ))))
            val trades = getTradesRepository.getTradesRemote(1L)
            then("Remote source is called ") {
                verify(api).getTrades(1L)

            }
            then("List of trades is returned ") {
                assert(trades.trades.size == 1)
                assert(trades.trades[0].pair == 1L)
                assert(trades.trades[0].trade_type == "sell")

            }
        }
        `when`("Trades are coming from Local/Persosted Source") {
            val db = mock<DbInterface> {}
            val api = mock<BxApi> {}
            val getTradesRepository = GetTradesRepositoryImpl(db, api)
            whenever(db.queryTrades(1L)).thenReturn(listOf(Trade(
                    rate = 1.0,
                    amount = 2.0,
                    pair = 1L,
                    trade_date = "",
                    trade_id = 10L,
                    trade_type = "sell"
            )))
            val trades = getTradesRepository.getTradesPersisted(1L)
            then("Remote source is called ") {
                verify(db).queryTrades(1L)

            }
            then("List of trades is returned ") {
                assert(trades.trades.size == 1)
                assert(trades.trades[0].pair == 1L)
                assert(trades.trades[0].trade_type == "sell")

            }
        }
    }
})
