package com.kotlin.client.domain


import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetTrades
import javax.inject.Inject


class GetTradesImpl @Inject constructor(private val tradesRepository: TradesRepository,
                                        private val pairsRepository: PairsRepository)
    : GetTrades {


    override fun getTrades(): List<Market> = pairsRepository.getPairs().map {
        Market(it, getTrades(it.id))
    }


    override fun getTrades(id: Long): Trades {
        val list = tradesRepository.getTradesPersisted(id)
        return if (list.trades.isEmpty()) {
            tradesRepository.getTradesRemote(id).trades.map {
                tradesRepository.save(it)
            }
            tradesRepository.getTradesPersisted(id)
        } else {
            list
        }
    }
}