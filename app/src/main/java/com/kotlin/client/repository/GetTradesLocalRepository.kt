package com.kotlin.client.repository

import com.kotlin.client.database.DbInterface
import com.kotlin.client.database.TradeDb
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import javax.inject.Inject

class GetTradesLocalRepository @Inject constructor(private val db: DbInterface)
    : GetTradesRepository {

    override fun save(trade: TradeDb) {
        db.insertTrade(trade)
    }

    override fun getTrades(id: Long): Trades = Trades(db.getTradeDb().map {
        Trade(trade_id = it.trade_id,
                trade_date = it.trade_date,
                trade_type = it.trade_type,
                amount = it.amount,
                rate = it.rate)
    }.toList())
}