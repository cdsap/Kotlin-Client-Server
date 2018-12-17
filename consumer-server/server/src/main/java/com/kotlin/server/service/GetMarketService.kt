package com.kotlin.server.service

import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import com.kotlin.core.usecases.GetMarket
import com.kotlin.core.usecases.GetTrades
import javax.inject.Inject


class GetMarketService @Inject constructor(private val getMarket: GetMarket) {

    fun getMarket(): List<Market> = getMarket.get()
}