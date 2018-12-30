package com.kotlin.server.service

import com.kotlin.core.entities.Trades
import com.kotlin.core.usecases.GetTrades
import javax.inject.Inject


class GetTradesService @Inject constructor(private val getTrades: GetTrades) {

    fun getTrades(pair: String): Trades = getTrades.getTrades(pair.toLong())
}
