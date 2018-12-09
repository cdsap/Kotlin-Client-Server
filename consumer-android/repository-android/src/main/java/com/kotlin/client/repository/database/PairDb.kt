package com.kotlin.client.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity
class PairDb(
        @PrimaryKey
        val id: Long,
        @ForeignKey(entity = SymbolDb::class,
                parentColumns = ["id"],
                childColumns = ["primaryPairId", "secondaryPairId"])
        val primaryPairId: String,
        val secondaryPairId: String,
        val lastPrice: Double,
        val volume: Double
)