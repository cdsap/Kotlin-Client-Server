package com.kotlin.server.repository

import com.googlecode.objectify.Objectify
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.TradesRepository
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.queryTrades
import com.kotlin.server.repository.mapper.MapperToTradeStore

class GetTradesRepositoryImpl(private val db: Objectify,
                              private val api: BxApi) : TradesRepository {

    private val mapperToTradeStore = MapperToTradeStore()

    override fun save(trade: Trade) {
        db.save().entity(mapperToTradeStore.transform(trade))
    }

    override fun getTradesRemote(id: Long): Trades = api.getTrades(id)

    override fun getTradesPersisted(id: Long): Trades = Trades(db.queryTrades(id))

}

