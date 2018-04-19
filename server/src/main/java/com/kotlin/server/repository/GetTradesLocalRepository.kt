package com.kotlin.server.repository

import com.googlecode.objectify.Objectify
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import com.kotlin.server.database.TradeStore
import javax.inject.Inject

class GetTradesLocalRepository @Inject constructor(private val objectify: Objectify)
    : GetTradesRepository {

    companion object {
        private const val FIELD = "__key__"
        private const val DIRECTION = "-"
        const val ORDER = "$DIRECTION$FIELD"
    }

    override fun save(trade: TradeStore) {
        objectify.save().entity(trade)
    }

    override fun getTrades(id: Long): Trades {
        val trades = mutableListOf<Trade>()
        objectify.load().type(TradeStore::class.java)
                .limit(50)
                .order(ORDER)
                .list().map {
                    trades.add(Trade(trade_date = it.trade_date, trade_id = it.trade_id,
                            trade_type = it.trade_type, amount = it.amount, rate = it.rate))
                }.toList()
        return Trades(trades)
    }
}