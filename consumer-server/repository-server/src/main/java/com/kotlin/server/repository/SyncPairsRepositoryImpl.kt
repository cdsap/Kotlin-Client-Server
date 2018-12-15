package com.kotlin.server.repository

import com.googlecode.objectify.Key
import com.googlecode.objectify.Objectify
import com.kotlin.core.entities.Market
import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.SyncRepository
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.PairStore
import com.kotlin.server.repository.database.TradeStore

class SyncPairsRepositoryImpl(private val db: Objectify,
                              private val api: BxApi) : SyncRepository {
    override fun get(): List<Market> {
        val markets = mutableListOf<Market>()
        db.load().type(PairStore::class.java).forEach {
            val trades = mutableListOf<Trade>()
            db.load().type(TradeStore::class.java)
                    .filter("pair", Key.create(PairStore::class.java, it.id))
                    .limit(50)
                    .order(GetTradesRepositoryImpl.ORDER)
                    .list().map {
                        trades.add(Trade(trade_date = it.trade_date, trade_id = it.trade_id,
                                trade_type = it.trade_type, amount = it.amount, rate = it.rate,
                                pair = it.pair.get().id))
                    }.toList()

            markets.add(Market(PairSymbol(id = it.id,
                    primarySymbol = it.primaryPairId,
                    secondarySymbol = it.secondaryPairId,
                    volume = it.volume,
                    rate = it.rate), trades = Trades(trades)))
        }

        return markets
    }


    override fun sync(): List<Market> {
        api.getPairsInfo().pairInfoList.forEach {

            if (db.load().type(PairStore::class.java).id(it.pairing_id).now() == null) {
                db.save().entity(PairStore(
                        id = it.pairing_id,
                        volume = it.volume_24_hours,
                        rate = it.last_price,
                        primaryPairId = it.primary_currency,
                        secondaryPairId = it.secondary_currency)
                )

            } else {
                val pairStore = db.load().type(PairStore::class.java)
                        .id(it.pairing_id).safe()
                pairStore.volume = it.volume_24_hours
                pairStore.rate = it.last_price

                db.save().entity(pairStore)
            }

        }
        return mutableListOf()
    }
}