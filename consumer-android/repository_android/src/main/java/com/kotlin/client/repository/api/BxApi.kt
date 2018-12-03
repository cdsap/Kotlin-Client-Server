package com.kotlin.client.api

import android.util.Log
import com.google.gson.GsonBuilder
import com.kotlin.client.repository.api.Api
import com.kotlin.client.repository.api.MarketOverall
import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BxApi {

    companion object {
        const val URL = "gpgpgp" // todo BuildConfig.URL
    }

    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(createGsonConverterPair())
                .build()

        api = retrofit.create(Api::class.java)
    }

    fun createGsonConverterPair(): Converter.Factory {
        val gsonBuilder = GsonBuilder()
        System.out.println("yyyy")
        gsonBuilder.registerTypeAdapter(MarketOverall::class.java, OverallDesrializer())
        gsonBuilder.registerTypeAdapter(Trades::class.java, Trades2Serial())
        val gson = gsonBuilder.create()
        return GsonConverterFactory.create(gson)
    }

    fun getTrades(pair: Long): Trades {
        return try {
            val a = api.getTrades(pair).execute()
            Log.e("inaki","kkkdkdkdkdkdk"+pair)
            a.body()!!

        } catch (e: Exception) {
            Log.e("inaki","sllslslslsls +e.t"+e.message)
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
