package com.kotlin.client.repository.api

import com.google.gson.*
import com.kotlin.core.domain.entities.Market
import java.lang.reflect.Type

class MarketDeserializer : JsonDeserializer<MarketOverall> {

    companion object {
        const val ENTITY = "items"
    }

    private val gson = Gson()

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): MarketOverall {
        val trades = mutableListOf<Market>()
        json.asJsonObject.get(ENTITY).asJsonArray.forEach {
            val trade = gson.fromJson(it, Market::class.java)
            trades.add(trade)
        }
        return MarketOverall(trades)
    }
}
