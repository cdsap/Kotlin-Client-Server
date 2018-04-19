package com.kotlin.core.network

import com.google.gson.GsonBuilder
import com.kotlin.core.entities.Trades
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

class GsonConverter {

    companion object {
        fun createGsonConverter(): Converter.Factory {
            val gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(Trades::class.java, TradesDeserializer())
            val gson = gsonBuilder.create()
            return GsonConverterFactory.create(gson)
        }
    }


}