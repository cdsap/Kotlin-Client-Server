package com.kotlin.client.repository

import com.kotlin.client.database.DbInterface
import com.kotlin.client.database.PairDb
import com.kotlin.client.database.TradeDb
import com.kotlin.client.repository.api.BxApi
import com.kotlin.core.entities.Market
import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.entities.Trade
import com.kotlin.core.repository.PairsRepository

class PairRepositoryImpl(val db: DbInterface,
                         val api: BxApi) : PairsRepository {

    override fun syncPairs(): List<PairSymbol> {
        api.syncTrades().forEach {
            if (db.getPairs(it.pairSymbol.id) == null) {
                db.insertPair(pairDb(it))
            } else {
                db.updatePair(pairDb(it))
            }
            it.trades.trades.map { trade ->
                db.insertTrade(tradeDb(trade))
            }
        }
        return getPairs()
    }

    override fun getPairs(): List<PairSymbol> {
        return db.getPairs().map {
            pairSymbol(it)
        }
    }

    private fun tradeDb(it: Trade): TradeDb {
        return TradeDb(trade_type = it.trade_type,
                trade_date = it.trade_date,
                trade_id = it.trade_id,
                amount = it.amount,
                rate = it.rate,
                pair = it.pair)
    }

    private fun pairSymbol(it: PairDb): PairSymbol {
        return PairSymbol(id = it.id,
                primarySymbol = it.primaryPairId,
                secondarySymbol = it.secondaryPairId,
                volume = it.volume,
                rate = it.lastPrice)
    }

    private fun pairDb(it: Market): PairDb {
        return PairDb(
                id = it.pairSymbol.id,
                volume = it.pairSymbol.volume,
                primaryPairId = it.pairSymbol.primarySymbol,
                secondaryPairId = it.pairSymbol.secondarySymbol,
                lastPrice = it.pairSymbol.rate
        )
    }

}