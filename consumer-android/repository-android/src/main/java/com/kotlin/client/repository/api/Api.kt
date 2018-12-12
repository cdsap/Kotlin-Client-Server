package com.kotlin.client.repository.api

import com.google.gson.annotations.SerializedName
import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

data class MarketOverall(@SerializedName("items") val items: List<Market>)


interface Api {

    @GET("api/v1/trades/{id}")
    fun getTrades(@Path("id") pair: Long): Call<Trades>

    @GET("api/v1/trades")
    fun syncTrades(): Call<MarketOverall>
}
