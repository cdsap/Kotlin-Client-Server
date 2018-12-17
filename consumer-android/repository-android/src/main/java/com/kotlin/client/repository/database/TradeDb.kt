package com.kotlin.client.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class TradeDb(
        @ForeignKey(entity = PairDb::class,
                childColumns = arrayOf("pair"),
                parentColumns = arrayOf("id"))
        @PrimaryKey
        var trade_id: Long = 0,
        var rate: Double = 0.0,
        var amount: Double = 0.0,
        var trade_date: String = "",
        var trade_type: String = "",
        var pair: Long = 1L
)