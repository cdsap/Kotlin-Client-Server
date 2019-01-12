package com.kotlin.server.repository.domain

import com.kotlin.core.domain.entities.Market
import com.kotlin.core.domain.entities.repository.SyncRepository
import com.kotlin.core.domain.entities.usecases.GetMarket


class GetMarketImpl(private val syncRepository: SyncRepository)
    : GetMarket {

    override fun get(): List<Market> = syncRepository.get()
}
