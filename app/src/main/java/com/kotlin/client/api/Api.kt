package com.kotlin.client.api

import com.google.gson.annotations.SerializedName
import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class MarketOverall(@SerializedName("items") val items: List<Market>)


interface Api {

    @GET("droidcon/v1/trades/?")
    fun getTrades(@Query("pair") pair: Long): Call<Trades>

    @GET("droidcon/v1/trades")
    fun syncTrades(): Call<MarketOverall>
}
