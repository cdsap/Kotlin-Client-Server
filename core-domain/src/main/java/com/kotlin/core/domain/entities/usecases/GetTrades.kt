package com.kotlin.core.domain.entities.usecases

import com.kotlin.core.domain.entities.Trades

interface GetTrades {
    fun getTrades(id: Long): Trades

    fun refreshTrades(id: Long): Trades
}