package com.kotlin.client.repository.api

import com.google.gson.GsonBuilder
import com.kotlin.client.api.OverallDesrializer
import com.kotlin.client.api.Trades2Serial
import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
