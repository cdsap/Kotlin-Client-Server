package com.kotlin.server.repository.api.entities

import com.google.gson.annotations.SerializedName

data class PairsInfo(val pairInfoList: List<PairInfo>)

data class PairInfo(@SerializedName("pairing_id") val pairing_id: Long = 1L,
                    @SerializedName("last_price") val last_price: Double = 0.0,
                    @SerializedName("volume_24hours") val volume_24_hours: Double = 0.0,
                    @SerializedName("primary_currency") val primary_currency: String = "",
                    @SerializedName("secondary_currency") val secondary_currency: String = "")

