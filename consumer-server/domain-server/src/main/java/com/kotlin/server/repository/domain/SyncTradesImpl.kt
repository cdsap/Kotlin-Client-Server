package com.kotlin.server.repository.domain

import com.kotlin.core.domain.entities.repository.PairsRepository
import com.kotlin.core.domain.entities.repository.TradesRepository
import com.kotlin.core.domain.entities.usecases.SyncTrades


class SyncTradesImpl(
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
