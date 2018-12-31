package com.kotlin.server.repository.mapper

import com.googlecode.objectify.Ref
import com.kotlin.core.entities.Trade
import com.kotlin.core.mapper.Mapper
import com.kotlin.server.repository.database.PairStore
import com.kotlin.server.repository.database.TradeStore

class MapperToTradeStore : Mapper<Trade, TradeStore> {
    override fun transform(origin: Trade): TradeStore {
        return TradeStore(
                trade_id = origin.trade_id,
                rate = origin.rate,
                amount = origin.amount,
                trade_date = origin.trade_date,
                trade_type = origin.trade_type,
                pair = Ref.create(PairStore(origin.pair)))
    }
}
