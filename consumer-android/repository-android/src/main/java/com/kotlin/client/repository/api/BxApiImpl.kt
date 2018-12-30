package com.kotlin.client.repository.api

import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import retrofit2.Retrofit

class BxApiImpl(retrofit: Retrofit) : BxApi {

    private val api: Api = retrofit.create(Api::class.java)


    override fun getTrades(pair: Long): Trades {
        return try {
            val a = api.getTrades(pair).execute()
            a.body()!!

        } catch (e: Exception) {
            Trades(listOf())
        }
    }

    override fun syncTrades(): List<Market> =
            try {
                val a = api.syncTrades().execute()
                a.body()!!.items
            } catch (e: Exception) {
                emptyList()
            }

}
