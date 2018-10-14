package com.kotlin.server.repository

import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import com.kotlin.server.api.BxApi
import com.kotlin.server.database.TradeStore

class GetTradesRepositoryImpl(private val db: Objectify,
                              private val api: BxApi) : GetTradesRepository {
    companion object {
        private const val FIELD = "__key__"
        private const val DIRECTION = "-"
        const val ORDER = "$DIRECTION$FIELD"
    }


    override fun save(trade: TradeStore) {
        db.save().entity(trade)
    }

    override fun getTrades(id: Long): Trades {
        val trades = mutableListOf<Trade>()
        return api.getTrades(id)
        // todo make the things work here
//        db.load().type(TradeStore::class.java)
//                .limit(50)
//                .order(GetTradesLocalRepository.ORDER)
//                .list().map {
//                    trades.add(Trade(trade_date = it.trade_date, trade_id = it.trade_id,
//                            trade_type = it.trade_type, amount = it.amount, rate = it.rate))
//                }.toList()
//        return Trades(trades)
    }

}