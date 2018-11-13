package com.kotlin.client.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TradeDb::class, PairDb::class, SymbolDb::class],
        version = 4,
        exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dbInterface(): DbInterface
}