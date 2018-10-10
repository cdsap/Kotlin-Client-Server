package com.kotlin.client.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class SymbolDb(
        @PrimaryKey
        val symbol: String)