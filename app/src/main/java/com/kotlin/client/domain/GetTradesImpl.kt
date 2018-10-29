package com.kotlin.client.domain


import com.kotlin.client.database.TradeDb
import com.kotlin.client.repository.GetTradesRepository
import com.kotlin.core.entities.Trades
import com.kotlin.core.usecases.GetTrades
import javax.inject.Inject


class GetTradesImpl @Inject constructor(private val repository: GetTradesRepository)
    : GetTrades {
    override fun getTrades(): List<Trades> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTrades(id: Long): Trades {
        val list = repository.getTradesPersisted(id)
        return if (list.trades.isEmpty()) {
            repository.getTradesRemotely(id).trades.map {
                val trade = TradeDb(trade_type = it.trade_type,
                        trade_date = it.trade_date,
                        trade_id = it.trade_id,
                        amount = it.amount,
                        rate = it.rate,
                        pair = 1L)
                repository.save(trade)
            }
            repository.getTradesPersisted(id)
        } else {
            list
        }
    }
}