package com.kotlin.server.domain

import com.googlecode.objectify.ObjectifyService
import com.googlecode.objectify.Ref
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.usecases.SyncTrades
import com.kotlin.server.database.PairStore
import com.kotlin.server.database.TradeStore
import javax.inject.Inject
import com.kotlin.core.repository.TradesRepository


class SyncTradesImpl @Inject constructor(
        private val tradesRepository: TradesRepository<TradeStore>,
        private val pairRepository: PairsRepository)
    : SyncTrades {


    override fun syncTrades() {
        pairRepository.getPairs().map {
            val idx = it.id
            tradesRepository.getTradesRemote(it.id).trades
                    .map {
                        tradesRepository.save(TradeStore(trade_type = it.trade_type,
                                trade_date = it.trade_date,
                                trade_id = it.trade_id,
                                rate = it.rate,
                                amount = it.amount,
                                pair = Ref.create(ObjectifyService.ofy()
                                        .load()
                                        .type(PairStore::class.java)
                                        .id(idx)
                                        .safe())))
                    }
        }
    }
}