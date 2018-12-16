package com.kotlin.server.repository.domain

import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.SyncTrades
import javax.inject.Inject


class SyncTradesImpl @Inject constructor(
        private val tradesRepository: TradesRepository,
        private val pairRepository: PairsRepository)
    : SyncTrades {

    override fun syncTrades(): Unit =
            pairRepository.getPairs().forEach { pair ->
                tradesRepository.getTradesRemote(pair.id).trades.forEach {
                    tradesRepository.save(it.copy(pair = pair.id))
                }
            }
}
