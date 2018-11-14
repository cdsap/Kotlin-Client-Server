package com.kotlin.client.api

import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trades
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("droidcon/v1/trades/?")
    fun getTrades(@Query("pair") pair: Long): Call<Trades>

    @GET("droidcon/v1/trades")
    fun syncTrades(): Call<List<Market>>
}
