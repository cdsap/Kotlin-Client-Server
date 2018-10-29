package com.kotlin.server.domain

import com.kotlin.core.entities.Trades
import com.kotlin.core.usecases.GetTrades
import com.kotlin.server.database.TradeStore
import com.kotlin.server.repository.GetTradesRepository
import com.kotlin.server.repository.PairRepositoryImpl
import com.kotlin.server.repository.PairsRepository
import javax.inject.Inject

class GetTradesImpl @Inject constructor(
        private val repository: GetTradesRepository,
        private val pairRepositoryImpl: PairsRepository) : GetTrades {


    override fun getTrades(): List<Trades> {
        val tradeList = mutableListOf<Trades>()
        pairRepositoryImpl.getPairs().forEach {
            tradeList.add(getTrades(it.id))
        }
        return tradeList
    }

    override fun getTrades(id: Long): Trades {
        val trades = repository.getTradesPersisted(id)
        return trades
    }
}