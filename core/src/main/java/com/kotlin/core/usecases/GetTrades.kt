package com.kotlin.core.usecases

import com.kotlin.core.entities.PairAndTrades
import com.kotlin.core.entities.Trades

interface GetTrades {
    fun getTrades(id: Long): Trades

    fun getTrades(): List<PairAndTrades>
}