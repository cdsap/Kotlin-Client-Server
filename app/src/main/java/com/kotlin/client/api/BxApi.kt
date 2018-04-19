package com.kotlin.client.api

import com.kotlin.client.BuildConfig
import com.kotlin.core.entities.Trades
import com.kotlin.core.network.GsonConverter
import retrofit2.Retrofit

class BxApi {

    companion object {
        const val URL = BuildConfig.URL
    }

    private val api: Api

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverter.createGsonConverter())
                .build()

        api = retrofit.create(Api::class.java)
    }

    fun getTrades(): Trades {
        return try {
            val a = api.getTrades().execute()
            a.body()!!

        } catch (e: Exception) {
            Trades(listOf())
        }
    }
}
