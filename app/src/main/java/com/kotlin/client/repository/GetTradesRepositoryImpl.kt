package com.kotlin.client.repository

import android.util.Log
import com.kotlin.client.api.BxApi
import com.kotlin.client.database.DbInterface
import com.kotlin.client.database.PairDb
import com.kotlin.client.database.TradeDb
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.TradesRepository

class GetTradesRepositoryImpl(private val db: DbInterface,
                              private val api: BxApi) : TradesRepository<TradeDb> {

    override fun getTradesPersisted(id: Long): Trades = Trades(db.getTradeDb(id).map {
        Trade(trade_id = it.trade_id,
                trade_date = it.trade_date,
                trade_type = it.trade_type,
                amount = it.amount,
                rate = it.rate)
    }.toList())

    override fun getTradesRemote(id: Long): Trades = api.getTrades(id)

    override fun save(trade: TradeDb) {
        db.insertTrade(trade)
    }

}