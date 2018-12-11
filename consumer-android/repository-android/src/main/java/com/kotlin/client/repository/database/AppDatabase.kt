package com.kotlin.client.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
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
                    .addCallback(
                            object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    Log.e("inaki", "xx")

                                    ioThread {
                                        Log.e("inaki", "cc")
                                        getInstance(context).dbInterface().insertSymbol(SymbolDb("THB"))
                                        getInstance(context).dbInterface().insertSymbol(SymbolDb("BTC"))
                                        getInstance(context).dbInterface().insertSymbol(SymbolDb("OMG"))
                                        getInstance(context).dbInterface().insertSymbol(SymbolDb("XRP"))
                                        getInstance(context).dbInterface().insertSymbol(SymbolDb("ETH"))
                                        getInstance(context).dbInterface().insertPair(PairDb(1, "THB", "BTC", 0.0, 0.0))
                                        getInstance(context).dbInterface().insertPair(PairDb(25, "THB", "XRP", 0.0, 0.0))
                                        getInstance(context).dbInterface().insertPair(PairDb(26, "THB", "OMG", 0.0, 0.0))
                                        getInstance(context).dbInterface().insertPair(PairDb(21, "THB", "ETH", 0.0, 0.0))
                                    }
                                }
                            })
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