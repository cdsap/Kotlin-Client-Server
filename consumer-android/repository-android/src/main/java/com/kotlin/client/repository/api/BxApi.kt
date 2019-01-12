package com.kotlin.client.repository.api

import com.kotlin.core.domain.entities.Market
import com.kotlin.core.domain.entities.Trades

interface BxApi {

    fun getTrades(pair: Long): Trades

    fun syncTrades(): List<Market>
}
