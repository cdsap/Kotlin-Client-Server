package com.kotlin.client.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity
class PairDb(
        @PrimaryKey
        val id: Long,
        @ForeignKey(entity = SymbolDb::class,
                parentColumns = ["id"],
                childColumns = ["primaryPairId", "secondaryPairId"])
        val primaryPairId: String,
        val secondaryPairId: String
)