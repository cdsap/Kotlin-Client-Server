package com.kotlin.server.repository.api

import com.kotlin.core.domain.entities.Trades
import com.kotlin.server.repository.api.entities.PairsInfo
import retrofit2.Retrofit

class BxApiImpl(retrofit: Retrofit) : BxApi {

    private val bx = retrofit.create(Bx::class.java)

    override fun getTrades(id: Long): Trades {
        val a = bx.getTrades(id).execute()
        return a?.body() ?: Trades(listOf())
    }

    override fun getPairsInfo(): PairsInfo {
        val a = bx.getPairInfo().execute()
        return a?.body()!!
    }
}
