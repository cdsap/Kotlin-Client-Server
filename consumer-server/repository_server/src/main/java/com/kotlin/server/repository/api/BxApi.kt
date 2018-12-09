package com.kotlin.server.repository.api

import com.google.gson.*
import com.kotlin.core.entities.Trades
import com.kotlin.core.network.TradesDeserializer
import com.kotlin.server.repository.api.patch.CallFactoryWrapper
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

    private fun createGsonConverterPair(): Converter.Factory {
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


        private val gson = Gson()

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): PairsInfo {
            val value = mutableListOf<PairInfo>()
            for ((_, pair) in json.asJsonObject.entrySet()) {
                val pairInfo = gson.fromJson(pair, PairInfo::class.java)
                value.add(pairInfo)
            }
            return PairsInfo(value)
        }
    }

}
