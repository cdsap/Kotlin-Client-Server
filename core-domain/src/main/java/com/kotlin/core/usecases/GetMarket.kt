package com.kotlin.core.usecases

import com.kotlin.core.entities.Market

interface GetMarket {
    fun get(): List<Market>
}