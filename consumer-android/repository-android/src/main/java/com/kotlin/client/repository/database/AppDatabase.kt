package com.kotlin.client.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TradeDb::class, PairDb::class],
        version = 6,
        exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dbInterface(): DbInterface

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext,
                    AppDatabase::
                    class.java,
                    "bx.db")
                    .build()
        }
    }
}