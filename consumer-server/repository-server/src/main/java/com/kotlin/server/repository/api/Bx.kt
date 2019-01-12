package com.kotlin.server.repository.api

import com.kotlin.core.domain.entities.Trades
import com.kotlin.server.repository.api.entities.PairsInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Bx {

    @GET("/api/trade/?")
    fun getTrades(@Query("pairing") pairing_id: Long): Call<Trades>

    @GET("/api/")
    fun getPairInfo(): Call<PairsInfo>
}

