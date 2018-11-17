package com.kotlin.server.api

import com.google.gson.*
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import com.kotlin.core.network.GsonConverter
import com.kotlin.core.network.TradesDeserializer
import com.kotlin.server.api.patch.CallFactoryWrapper
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class BxApi {

    private val bx: Bx

    companion object {
        const val URL = "https://bx.in.th"
    }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(createGsonConverterPair())
                .callFactory(CallFactoryWrapper())
                .build()

        bx = retrofit.create(Bx::class.java)
    }

    fun createGsonConverterPair(): Converter.Factory {
        val gsonBuilder = GsonBuilder()
        System.out.println("yyyy")
        gsonBuilder.registerTypeAdapter(PairsInfo::class.java, PairsDeserializer())
        gsonBuilder.registerTypeAdapter(Trades::class.java, TradesDeserializer())
        val gson = gsonBuilder.create()
        return GsonConverterFactory.create(gson)
    }

    fun getTrades(id: Long): Trades {
        val a = bx.getTrades(id).execute()
        return a?.body() ?: Trades(listOf())
    }

    fun getPairsInfo(): PairsInfo {
        val a = bx.getPairInfo().execute()
        return a?.body()!!
    }


    class PairsDeserializer : JsonDeserializer<PairsInfo> {

        companion object {
            const val TRADES_ENTITY = "trades"
        }

        private val gson = Gson()

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): PairsInfo {
            System.out.println("xxxxxxx")
            val pairsInfo = mutableListOf<PairInfo>()
            println("sllslsls" + json.asJsonObject.toString())
            val value = mutableListOf<PairInfo>()
            for ((key, valuex) in json.asJsonObject.entrySet()) {
                println("++++++")
                println(valuex)
                val pairInfo = gson.fromJson(valuex, PairInfo::class.java)
                value.add(pairInfo)
            }
            return PairsInfo(value)
        }
    }

}
