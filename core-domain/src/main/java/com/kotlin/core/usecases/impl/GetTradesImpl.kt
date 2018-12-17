package com.kotlin.core.usecases.impl


import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetTrades


class GetTradesImpl(private val tradesRepository: TradesRepository)
    : GetTrades {

    override fun refreshTrades(id: Long): Trades {
        tradesRepository.getTradesRemote(id).trades.map {
            tradesRepository.save(it)
        }
        return tradesRepository.getTradesPersisted(id)
    }


    override fun getTrades(id: Long): Trades {
        val list = tradesRepository.getTradesPersisted(id)
        return if (list.trades.isEmpty()) {
            refreshTrades(id)
        } else {
            list
        }
    }
}