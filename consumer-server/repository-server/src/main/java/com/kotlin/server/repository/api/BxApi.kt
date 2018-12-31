package com.kotlin.server.repository.api

import com.kotlin.core.entities.Trades
import com.kotlin.server.repository.api.entities.PairsInfo


interface BxApi {
    fun getTrades(id: Long): Trades
    fun getPairsInfo(): PairsInfo
}