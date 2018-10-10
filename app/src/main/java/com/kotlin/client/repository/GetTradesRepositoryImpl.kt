package com.kotlin.client.repository

import com.kotlin.client.api.BxApi
import com.kotlin.client.database.DbInterface
import com.kotlin.client.database.TradeDb
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades

class GetTradesRepositoryImpl(private val db: DbInterface,
                              private val api: BxApi) : GetTradesRepository {

    override fun getLastTradesById(id: Long): Trade {
        db.getLastTradesById(id).let {
            return Trade(trade_id = it.trade_id,
                    trade_date = it.trade_date,
                    trade_type = it.trade_type,
                    amount = it.amount,
                    rate = it.rate)
        }
    }

    override fun getTradesPersisted(id: Long): Trades = Trades(db.getTradeDb(id).map {
        Trade(trade_id = it.trade_id,
                trade_date = it.trade_date,
                trade_type = it.trade_type,
                amount = it.amount,
                rate = it.rate)
    }.toList())

    override fun getTradesRemotely(id: Long): Trades = api.getTrades()

    override fun save(trade: TradeDb) {
        db.insertTrade(trade)
    }

    override fun getTrades(id: Long): Trades {
        TODO("not implemented")
    }

}