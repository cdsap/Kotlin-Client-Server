package com.kotlin.server.repository.domain

import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.usecases.SyncTrades
import javax.inject.Inject
import com.kotlin.core.repository.TradesRepository


class SyncTradesImpl @Inject constructor(
        private val tradesRepository: TradesRepository,
        private val pairRepository: PairsRepository)
    : SyncTrades {


    override fun syncTrades() {
        pairRepository.getPairs().map {
            val idx = it.id
            tradesRepository.getTradesRemote(it.id).trades
                    .map {
                   //     tradesRepository.save()
                  //      tradesRepository.save(TradeStore(trade_type = it.trade_type,
//                                trade_date = it.trade_date,
//                                trade_id = it.trade_id,
//                                rate = it.rate,
//                                amount = it.amount,
//                                pair = Ref.create(ObjectifyService.ofy()
//                                        .load()
//                                        .type(PairStore::class.java)
//                                        .id(idx)
//                                        .safe())))
                    }
        }
    }
}