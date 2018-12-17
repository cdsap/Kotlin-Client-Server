package com.kotlin.client.repository.api

import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import retrofit2.Retrofit

class BxApi(val retrofit: Retrofit) {

    private val api: Api = retrofit.create(Api::class.java)


    fun getTrades(pair: Long): Trades {
        return try {
            val a = api.getTrades(pair).execute()
            a.body()!!

        } catch (e: Exception) {
            Trades(listOf())
        }
    }

    fun syncTrades(): List<Market> =
            try {
                val a = api.syncTrades().execute()
                a.body()!!.items
            } catch (e: Exception) {
                emptyList<Market>()
            }

}
