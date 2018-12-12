package com.kotlin.client.domain


import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.SyncRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetTrades
import javax.inject.Inject


class GetTradesImpl @Inject constructor(private val repository: TradesRepository,
                                        private val pairsRepository: PairsRepository,
                                        private val syncRepository: SyncRepository)
    : GetTrades {


    override fun getTrades(): List<Market> {
        val pairs = pairsRepository.getPairs()
        val tradeList = mutableListOf<Market>()
        return if (pairs.isEmpty()) {
            syncRepository.sync()
        } else {
            pairs.forEach {
                tradeList.add(Market(it, getTrades(it.id)))
            }
            tradeList
        }
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