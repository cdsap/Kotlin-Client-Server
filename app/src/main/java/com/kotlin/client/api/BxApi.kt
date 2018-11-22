package com.kotlin.client.api

import android.util.Log
import com.google.gson.GsonBuilder
import com.kotlin.client.BuildConfig
import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import com.kotlin.core.network.GsonConverter
import com.kotlin.core.network.TradesDeserializer
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BxApi {

    companion object {
        const val URL = BuildConfig.URL
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
        gsonBuilder.registerTypeAdapter(Trades::class.java, TradesDeserializer())
        val gson = gsonBuilder.create()
        return GsonConverterFactory.create(gson)
    }

    fun getTrades(pair: Long): Trades {
        return try {
            val a = api.getTrades(pair).execute()
            a.body()!!

        } catch (e: Exception) {
            Trades(listOf())
        }
    }

    fun syncTrades(): List<Market> {
        //      try {
        val a = api.syncTrades().execute()
        Log.e("inaki", " a.body()!!   " + a.message())
        Log.e("inaki", " a.body()!!   " + a.message())
        Log.e("inaki", " a.body()!!   ")
//            Log.e("inaki", " a.body()!!   " + a.body()!!)
        Log.e("inaki", " a.body()!!   ")
        val x = a.body()?.let {
            Log.e("inaki", "skkskskskksksksks")
        }
        return a.body()!!.items
//        } catch (e: Exception) {
//            Log.e("inaki", "e " + e.message)
//            return emptyList<Market>()
//        }
    }
}
