package com.kotlin.client.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
class TradeDb(
        @ForeignKey(entity = PairDb::class,
                childColumns = arrayOf("pair"),
                parentColumns = arrayOf("id"))

        var trade_id: Long = 0,
        var rate: Double = 0.0,
        var amount: Double = 0.0,
        var trade_date: String = "",
        var trade_type: String = "",
        var pair: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}
