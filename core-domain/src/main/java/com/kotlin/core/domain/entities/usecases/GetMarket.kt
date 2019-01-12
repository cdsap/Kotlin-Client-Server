package com.kotlin.core.domain.entities.usecases

import com.kotlin.core.domain.entities.Market

interface GetMarket {
    fun get(): List<Market>
}