package com.kotlin.server.repository

import com.googlecode.objectify.Key
import com.googlecode.objectify.Objectify
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import com.kotlin.server.api.BxApi
import com.kotlin.server.database.PairStore
import com.kotlin.server.database.TradeStore

class GetTradesRepositoryImpl(private val db: Objectify,
                              private val api: BxApi) : GetTradesRepository {


    override fun getTradesRemote(id: Long): Trades {
        return api.getTrades(id)
    }

    override fun getTradesPersisted(id: Long): Trades {
        var trades = mutableListOf<Trade>()
        db.load().type(TradeStore::class.java)
                .filter("pair", Key.create(PairStore::class.java, id))
                .limit(50)
                .order(ORDER)
                .list().map {
                    trades.add(Trade(trade_date = it.trade_date, trade_id = it.trade_id,
                            trade_type = it.trade_type, amount = it.amount, rate = it.rate,
                            pair = it.pair.get().id))
                }.toList()
        return Trades(trades)
    }

    companion object {
        private const val FIELD = "__key__"
        private const val DIRECTION = "-"
        const val ORDER = "$DIRECTION$FIELD"
    }


    override fun save(trade: TradeStore) {
        db.save().entity(trade)
    }
}
