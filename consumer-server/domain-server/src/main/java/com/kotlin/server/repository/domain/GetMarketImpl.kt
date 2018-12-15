package com.kotlin.server.repository.domain

import com.kotlin.core.entities.Market
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.SyncRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetMarket
import com.kotlin.core.usecases.SyncTrades
import javax.inject.Inject


class GetMarketImpl @Inject constructor(
            private val syncRepository: SyncRepository)
    : GetMarket {

    override fun get(): List<Market> = syncRepository.get()
}
