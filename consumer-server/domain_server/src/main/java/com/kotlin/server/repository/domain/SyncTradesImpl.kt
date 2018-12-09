package com.kotlin.server.repository.domain

import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.SyncTrades
import javax.inject.Inject


class SyncTradesImpl @Inject constructor(
        private val tradesRepository: TradesRepository,
        private val pairRepository: PairsRepository)
    : SyncTrades {

    override fun syncTrades() {
        pairRepository.getPairs().map {
            val pairId = it.id
            tradesRepository.getTradesRemote(it.id).trades
                    .map {
                        tradesRepository.save(it.copy(pair = pairId))
                    }
        }
    }
}