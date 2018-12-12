package com.kotlin.client.repository

import com.kotlin.client.database.DbInterface
import com.kotlin.client.database.PairDb
import com.kotlin.client.database.TradeDb
import com.kotlin.client.repository.api.BxApi
import com.kotlin.core.entities.Market
import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.SyncRepository

class SyncPairRepositoryImpl(private val db: DbInterface,
                             private val api: BxApi) : SyncRepository {
    override fun sync(): List<Market> {
        val marketList = mutableListOf<Market>()
        val pairs = db.getPairs()

        api.syncTrades().forEach {

            if (db.getPairs(it.pairSymbol.id) == null) {
                db.insertPair(PairDb(
                        id = it.pairSymbol.id,
                        volume = it.pairSymbol.volume,
                        primaryPairId = it.pairSymbol.primarySymbol,
                        secondaryPairId = it.pairSymbol.secondarySymbol,
                        lastPrice = it.pairSymbol.rate))
            } else {
                db.updatePair(PairDb(
                        id = it.pairSymbol.id,
                        volume = it.pairSymbol.volume,
                        primaryPairId = it.pairSymbol.primarySymbol,
                        secondaryPairId = it.pairSymbol.secondarySymbol,
                        lastPrice = it.pairSymbol.rate
                ))
            }

            it.trades.trades.map {
                db.insertTrade(TradeDb(trade_type = it.trade_type,
                        trade_date = it.trade_date,
                        trade_id = it.trade_id,
                        amount = it.amount,
                        rate = it.rate,
                        pair = it.pair))
            }
        }


        db.getPairs().forEach {
            val trades = mutableListOf<Trade>()
            db.getTradeDb(it.id).forEach {
                trades.add(Trade(
                        trade_date = it.trade_date,
                        trade_id = it.trade_id,
                        trade_type = it.trade_type,
                        amount = it.amount,
                        rate = it.rate)
                )
            }
            marketList.add(Market(PairSymbol(
                    id = it.id,
                    rate = it.lastPrice,
                    primarySymbol = it.primaryPairId,
                    secondarySymbol = it.secondaryPairId,
                    volume = it.volume), Trades(trades))
            )
        }

        return marketList

    }
}
