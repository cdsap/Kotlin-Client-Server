package com.kotlin.client.repository.api

import com.google.gson.*
import com.kotlin.core.entities.Market
import java.lang.reflect.Type

class OverallDesrializer : JsonDeserializer<MarketOverall> {

    companion object {
        const val TRADES_ENTITY = "items"
    }

    private val gson = Gson()

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): MarketOverall {
        val trades = mutableListOf<Market>()
        json.asJsonObject.get(TRADES_ENTITY).asJsonArray.forEach {
            val trade = gson.fromJson(it, Market::class.java)
            trades.add(trade)
        }
        return MarketOverall(trades)
    }
}
