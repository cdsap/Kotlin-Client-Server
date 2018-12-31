package com.kotlin.client.repository

import com.kotlin.client.repository.api.BxApi
import com.kotlin.client.repository.database.DbInterface
import com.kotlin.client.repository.database.TradeDb
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotlintest.specs.BehaviorSpec


class GetTradesRepositoryImplTest : BehaviorSpec({

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
            whenever(db.getTradeDb(1L)).thenReturn(listOf(TradeDb(
                    rate = 1.0,
                    amount = 2.0,
                    pair = 1L,
                    trade_date = "",
                    trade_id = 10L,
                    trade_type = "sell"
            )))
            val trades = getTradesRepository.getTradesPersisted(1L)
            then("Remote source is called ") {
                verify(db).getTradeDb(1L)

            }
            then("List of trades is returned ") {
                assert(trades.trades.size == 1)
                assert(trades.trades[0].pair == 1L)
                assert(trades.trades[0].trade_type == "sell")

            }
        }
        `when`("Saving Trade") {
            val db = mock<DbInterface> {}
            val api = mock<BxApi> {}
            val getTradesRepository = GetTradesRepositoryImpl(db, api)

            val trades = getTradesRepository.save(Trade(
                    rate = 1.0,
                    amount = 2.0,
                    pair = 1L,
                    trade_date = "",
                    trade_id = 10L,
                    trade_type = "sell"
            ))
            then("RTrade is transfdomerd ") {
                verify(db).insertTrade(argThat {
                    this.amount == 2.0 &&
                            rate == 1.0 &&
                            trade_date == "" &&
                            trade_id == 10L &&
                            trade_type == "sell"

                })

            }

        }
    }
})

