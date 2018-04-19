package com.kotlin.client.domain


import com.kotlin.client.database.TradeDb
import com.kotlin.client.repository.GetTradesRepository
import com.kotlin.core.entities.Trades
import com.kotlin.core.usecases.GetTrades
import javax.inject.Inject


class GetTradesImpl @Inject constructor(private val repositoryLocal: GetTradesRepository,
                                        private val repositoryRemote: GetTradesRepository)
    : GetTrades {

    override fun getTrades(id: Long): Trades {
        val list = repositoryLocal.getTrades(id)
        return if (list.trades.isEmpty()) {
            repositoryRemote.getTrades(id).trades.map {
                val trade = TradeDb(trade_type = it.trade_type,
                        trade_date = it.trade_date,
                        trade_id = it.trade_id,
                        amount = it.amount,
                        rate = it.rate)
                repositoryLocal.save(trade)
            }
            repositoryLocal.getTrades(id)
        } else {
            list
        }
    }
}