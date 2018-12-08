package com.kotlin.core.usecases.impl

import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetTrades

class GetTradesImpl (
        private val repository: TradesRepository,
        private val pairRepositoryImpl: PairsRepository) : GetTrades {

    override fun getTrades(): List<Market> {
        val tradeList = mutableListOf<Market>()
        pairRepositoryImpl.getPairs().forEach {
            tradeList.add(Market(it, getTrades(it.id)))
        }
        return tradeList
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