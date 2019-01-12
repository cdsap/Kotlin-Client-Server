package com.kotlin.client.repository.mapper

import com.kotlin.client.repository.database.TradeDb
import com.kotlin.core.domain.entities.Trade
import com.kotlin.core.domain.entities.mapper.Mapper

class MapperToTradeDb : Mapper<Trade, TradeDb> {
    override fun transform(origin: Trade): TradeDb {
        return TradeDb(trade_type = origin.trade_type,
                trade_date = origin.trade_date,
                trade_id = origin.trade_id,
                amount = origin.amount,
                rate = origin.rate,
                pair = origin.pair)
    }

}