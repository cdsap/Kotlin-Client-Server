package com.kotlin.client.domain


import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetTrades
import javax.inject.Inject


class GetTradesImpl @Inject constructor(private val repository: TradesRepository)
    : GetTrades {

    override fun getTrades(): List<Market> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTrades(id: Long): Trades {
        val list = repository.getTradesPersisted(id)
        return if (list.trades.isEmpty()) {
            repository.getTradesRemote(id).trades.map {
                repository.save(it)
            }
            repository.getTradesPersisted(id)
        } else {
            list
        }
    }
}