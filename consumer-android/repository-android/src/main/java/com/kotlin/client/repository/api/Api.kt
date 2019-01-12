package com.kotlin.client.repository.api

import com.google.gson.annotations.SerializedName
import com.kotlin.core.domain.entities.Market
import com.kotlin.core.domain.entities.Trades
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("api/v2/trades/{id}")
    fun getTrades(@Path("id") pair: Long): Call<Trades>

    @GET("api/v2/market")
    fun syncTrades(): Call<MarketOverall>
}


data class MarketOverall(@SerializedName("items") val items: List<Market>)

