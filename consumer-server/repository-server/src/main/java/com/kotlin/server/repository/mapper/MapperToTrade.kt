package com.kotlin.server.repository.mapper

import com.kotlin.core.entities.Trade
import com.kotlin.server.repository.database.TradeStore

class MapperToTrade : Mapper<TradeStore, Trade> {

    override fun transform(origin: TradeStore): Trade {
        return Trade(trade_date = origin.trade_date, trade_id = origin.trade_id,
                trade_type = origin.trade_type, amount = origin.amount, rate = origin.rate,
                pair = origin.pair.get().id)
    }

}