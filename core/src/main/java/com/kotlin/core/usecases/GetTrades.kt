package com.kotlin.core.usecases

import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades

interface GetTrades {
    fun getTrades(id: Long): Trades

    fun getTrades(): List<Market>
}