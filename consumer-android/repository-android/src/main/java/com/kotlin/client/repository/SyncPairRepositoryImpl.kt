package com.kotlin.client.repository

import com.kotlin.client.database.DbInterface
import com.kotlin.client.database.PairDb
import com.kotlin.client.database.TradeDb
import com.kotlin.client.repository.api.BxApi
import com.kotlin.core.repository.SyncRepository

class SyncPairRepositoryImpl(private val db: DbInterface,
                             private val api: BxApi) : SyncRepository {
    override fun sync() {
        api.syncTrades().forEach {
            db.updatePair(PairDb(
                    id = it.pairSymbol.id,
                    volume = it.pairSymbol.volume,
                    primaryPairId = it.pairSymbol.primarySymbol,
                    secondaryPairId = it.pairSymbol.secondarySymbol,
                    lastPrice = it.pairSymbol.rate
            ))

            it.trades.trades.map {
                db.insertTrade(TradeDb(trade_type = it.trade_type,
                        trade_date = it.trade_date,
                        trade_id = it.trade_id,
                        amount = it.amount,
                        rate = it.rate,
                        pair = it.pair))
            }
        }
    }
}
