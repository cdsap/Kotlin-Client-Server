package com.kotlin.client.domain


import com.kotlin.client.database.TradeDb
import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetTrades
import javax.inject.Inject


class GetTradesImpl @Inject constructor(private val repository: TradesRepository<TradeDb>)
    : GetTrades {
    override fun getTrades(): List<Market> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getTrades(id: Long): Trades {
        val list = repository.getTradesPersisted(id)
        return if (list.trades.isEmpty()) {
            repository.getTradesRemote(id).trades.map {
                val trade = TradeDb(trade_type = it.trade_type,
                        trade_date = it.trade_date,
                        trade_id = it.trade_id,
                        amount = it.amount,
                        rate = it.rate,
                        pair = it.pair)
                repository.save(trade)
            }
            repository.getTradesPersisted(id)
        } else {
            list
        }
    }
}