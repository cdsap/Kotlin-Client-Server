package com.kotlin.server.repository.domain

import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetTrades
import javax.inject.Inject

class GetTradesImpl @Inject constructor(
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
        val trades = repository.getTradesPersisted(id)
        return trades
    }
}