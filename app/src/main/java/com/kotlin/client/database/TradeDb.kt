package com.kotlin.client.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class TradeDb(
        var trade_id: Long = 0,
        var rate: Double = 0.0,
        var amount: Double = 0.0,
        var trade_date: String = "",
        var trade_type: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}
