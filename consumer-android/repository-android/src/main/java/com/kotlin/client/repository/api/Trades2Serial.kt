package com.kotlin.client.api

import android.util.Log
import com.google.gson.*
import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import java.lang.reflect.Type


class Trades2Serial : JsonDeserializer<Trades> {

    companion object {
        const val TRADES_ENTITY = "trades"
    }

    private val gson = Gson()

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Trades {
        val trades = mutableListOf<Trade>()
        Log.e("inaki","sss"+json)
        Log.e("inaki",""+json.asJsonObject.get(TRADES_ENTITY))
        json.asJsonObject.get(TRADES_ENTITY).asJsonArray.forEach {
            val trade = gson.fromJson(it, Trade::class.java)
            trades.add(trade)
        }
        Log.e("inaki","trades"+trades.count())

        return Trades(trades)
    }
}
