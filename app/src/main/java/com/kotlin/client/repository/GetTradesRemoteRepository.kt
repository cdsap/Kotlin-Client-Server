package com.kotlin.client.repository

import com.kotlin.client.api.BxApi
import com.kotlin.client.database.TradeDb
import com.kotlin.core.entities.Trades
import javax.inject.Inject

class GetTradesRemoteRepository @Inject constructor(private val api: BxApi) : GetTradesRepository {
    override fun save(trades: TradeDb) {
        throw NotImplementedError("RemoteRepository don't implement saving trades")
    }

    override fun getTrades(id: Long): Trades = api.getTrades()

}