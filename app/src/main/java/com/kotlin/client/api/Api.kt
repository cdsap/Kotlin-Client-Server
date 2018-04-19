package com.kotlin.client.api

import com.kotlin.core.entities.Trades
import retrofit2.Call
import retrofit2.http.GET


interface Api {
    @GET("droidcon/v1/trades/1")
    fun getTrades(): Call<Trades>
}

