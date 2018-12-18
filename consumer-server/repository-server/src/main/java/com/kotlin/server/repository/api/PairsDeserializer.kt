package com.kotlin.server.repository.api

import com.google.gson.*
import com.kotlin.server.repository.api.entities.PairInfo
import com.kotlin.server.repository.api.entities.PairsInfo
import java.lang.reflect.Type

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