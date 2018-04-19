package com.kotlin.server.repository

import com.kotlin.core.entities.Trades
import com.kotlin.server.api.BxApi
import com.kotlin.server.database.TradeStore
import javax.inject.Inject

class GetTradesRemoteRepository @Inject constructor(private val api: BxApi) : GetTradesRepository {

    override fun getTrades(id: Long): Trades {
        return api.getTrades(id)
    }

    override fun save(trade: TradeStore) {
        throw NotImplementedError("RemoteRepository don't implement saving trades")
    }

}