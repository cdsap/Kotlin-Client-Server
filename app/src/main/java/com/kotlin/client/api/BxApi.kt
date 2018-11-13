package com.kotlin.client.api

import com.kotlin.client.BuildConfig
import com.kotlin.core.entities.PairAndTrades
import com.kotlin.core.entities.Trades
import com.kotlin.core.network.GsonConverter
import retrofit2.Retrofit

class BxApi {

    companion object {
        const val URL = BuildConfig.URL
    }

    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverter.createGsonConverterTrade())
                .addConverterFactory(GsonConverter.createGsonConverterTrade())
                .build()

        api = retrofit.create(Api::class.java)
    }

    fun getTrades(pair: Long): Trades {
        return try {
            val a = api.getTrades(pair).execute()
            a.body()!!

        } catch (e: Exception) {
            Trades(listOf())
        }
    }

    fun syncTrades(): List<PairAndTrades> {
        return try {
            val a = api.syncTrades().execute()
            a.body()!!

        } catch (e: Exception) {
            emptyList<PairAndTrades>()
        }
    }
}
