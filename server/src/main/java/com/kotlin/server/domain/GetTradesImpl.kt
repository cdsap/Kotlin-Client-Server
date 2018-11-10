package com.kotlin.server.domain

import com.kotlin.core.entities.PairAndTrades
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


    override fun getTrades(): List<PairAndTrades> {
        val tradeList = mutableListOf<PairAndTrades>()
        pairRepositoryImpl.getPairs().forEach {

            tradeList.add(PairAndTrades(it, getTrades(it.id)))
        }
        return tradeList
    }

    override fun getTrades(id: Long): Trades {
        val trades = repository.getTradesPersisted(id)
        return trades
    }
}