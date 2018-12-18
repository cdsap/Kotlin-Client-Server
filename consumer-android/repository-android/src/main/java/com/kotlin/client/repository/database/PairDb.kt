package com.kotlin.client.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity
class PairDb(
        @PrimaryKey
        val id: Long,
        val primaryPairId: String,
        val secondaryPairId: String,
        val lastPrice: Double,
        val volume: Double
)