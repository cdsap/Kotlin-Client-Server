package com.kotlin.client.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kotlin.client.api.BxApi
import com.kotlin.client.database.AppDatabase
import com.kotlin.client.database.DbInterface
import com.kotlin.client.database.PairDb
import com.kotlin.client.database.SymbolDb
import com.kotlin.client.repository.GetTradesRepository
import com.kotlin.client.repository.GetTradesRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.launch
import javax.inject.Singleton

@Module
class RepositoryModule {
    lateinit var dbInterface: DbInterface

    @Provides
    fun providesRepository(db: DbInterface,
                           api: BxApi): GetTradesRepository =
            GetTradesRepositoryImpl(db, api)


    @Provides
    fun providesRestApi(): BxApi = BxApi()

    @Provides
    fun providesDbInterface(context: Context): DbInterface {
        dbInterface = Room.databaseBuilder(context,
                AppDatabase::class.java,
                "bx.db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        launch {
                            dbInterface.insertSymbol(SymbolDb("THB"))
                            dbInterface.insertSymbol(SymbolDb("BTC"))
                            dbInterface.insertSymbol(SymbolDb("OMG"))
                            dbInterface.insertSymbol(SymbolDb("XRP"))
                            dbInterface.insertSymbol(SymbolDb("ETH"))
                            dbInterface.insertPair(PairDb(1, "THB", "BTC"))
                            dbInterface.insertPair(PairDb(25, "THB", "XRP"))
                            dbInterface.insertPair(PairDb(26, "THB", "OMG"))
                            dbInterface.insertPair(PairDb(21, "THB", "ETH"))
                        }
                    }
                })
                .build()
                .dbInterface()
        return dbInterface
    }
}