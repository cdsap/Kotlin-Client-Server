package com.kotlin.server.api

import com.kotlin.core.entities.Trades
import com.kotlin.core.network.GsonConverter
import com.kotlin.server.api.patch.CallFactoryWrapper
import retrofit2.Retrofit

class BxApi {

    private val bx: Bx

    companion object {
        const val URL = "https://bx.in.th"
    }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverter.createGsonConverter())
                .callFactory(CallFactoryWrapper())
                .build()

        bx = retrofit.create(Bx::class.java)
    }

    fun getTrades(id: Long): Trades {
        val a = bx.getTrades(id).execute()
        return a?.body() ?: Trades(listOf())
    }
}
