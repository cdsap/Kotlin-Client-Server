package com.kotlin.server.service

import com.kotlin.core.domain.entities.Market
import com.kotlin.core.domain.entities.usecases.GetMarket
import javax.inject.Inject


class GetMarketService @Inject constructor(private val getMarket: GetMarket) {

    fun getMarket(): List<Market> = getMarket.get()
}
