package com.kotlin.core.network

import com.google.gson.*
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import java.lang.reflect.Type

class TradesDeserializer : JsonDeserializer<Trades> {

    companion object {
        const val TRADES_ENTITY = "trades"
    }

    private val gson = Gson()

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Trades {
        val trades = mutableListOf<Trade>()
        json.asJsonObject.get(TRADES_ENTITY).asJsonArray.forEach {
            trades.add(gson.fromJson(it, Trade::class.java))
        }
        return Trades(trades)
    }
}
