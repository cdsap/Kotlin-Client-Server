package com.kotlin.server.repository.api

import com.kotlin.core.entities.Trades
import com.kotlin.server.repository.api.entities.PairsInfo
import retrofit2.Retrofit

class BxApi(retrofit: Retrofit) {

    private val bx = retrofit.create(Bx::class.java)

    fun getTrades(id: Long): Trades {
        val a = bx.getTrades(id).execute()
        return a?.body() ?: Trades(listOf())
    }

    fun getPairsInfo(): PairsInfo {
        val a = bx.getPairInfo().execute()
        return a?.body()!!
    }
}
