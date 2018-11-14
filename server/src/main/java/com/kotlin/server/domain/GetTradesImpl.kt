package com.kotlin.server.domain

import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetTrades
import com.kotlin.server.database.TradeStore
import javax.inject.Inject

class GetTradesImpl @Inject constructor(
        private val repository: TradesRepository<TradeStore>,
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