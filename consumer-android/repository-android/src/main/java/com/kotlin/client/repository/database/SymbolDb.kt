package com.kotlin.client.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SymbolDb(
        @PrimaryKey
        val symbol: String)