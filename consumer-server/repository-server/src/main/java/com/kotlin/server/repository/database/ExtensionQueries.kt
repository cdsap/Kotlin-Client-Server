package com.kotlin.server.repository.database

import com.googlecode.objectify.Key
import com.googlecode.objectify.Objectify
import com.kotlin.core.entities.Trade
import com.kotlin.server.repository.mapper.MapperToTrade

fun Objectify.queryTrades(id: Long): List<Trade> {
    val mapperTradeStore = MapperToTrade()
    val trades = mutableListOf<Trade>()
    this.load().type(TradeStore::class.java)
            .filter("pair", Key.create(PairStore::class.java, id))
            .limit(50)
            .order(ORDER)
            .list().map {
                trades.add(mapperTradeStore.transform(it))
            }
    return trades
}

private const val FIELD = "__key__"
private const val DIRECTION = "-"
const val ORDER = "$DIRECTION$FIELD"
