package com.kotlin.server.api

import com.kotlin.core.entities.Trades
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Bx {

    @GET("/api/trade/?")
    fun getTrades(@Query("pairing") pairing_id: Long): Call<Trades>
}

