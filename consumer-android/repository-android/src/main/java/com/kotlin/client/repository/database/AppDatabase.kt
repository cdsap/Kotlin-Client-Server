package com.kotlin.client.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.Executors

@Database(entities = [TradeDb::class, PairDb::class, SymbolDb::class],
        version = 5,
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
            Log.e("inaki", "pp")
            return Room.databaseBuilder(context.applicationContext,
                    AppDatabase::
                    class.java,
                    "bx.db")

                    .build()
        }
    }
}

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}