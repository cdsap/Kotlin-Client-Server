package com.kotlin.client.api

import android.util.Log
import com.google.gson.*
import com.kotlin.core.entities.Market
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
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
            Log.e("inaki", "xkxkxkxkxxk")
            val trade = gson.fromJson(it, Market::class.java)
            trades.add(trade)
        }
        Log.e("inaki", "finishing")
        Log.e("inaki", "finishing")
        return MarketOverall(trades)
    }
}
