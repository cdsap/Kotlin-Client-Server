package com.kotlin.client.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kotlin.client.api.BxApi
import com.kotlin.client.database.*
import com.kotlin.client.repository.*
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.SyncRepository
import com.kotlin.core.repository.TradesRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Module
class RepositoryModule {
    lateinit var dbInterface: DbInterface

    @Provides
    fun providesTradeRepository(db: DbInterface,
                                api: BxApi): TradesRepository<TradeDb> =
            GetTradesRepositoryImpl(db, api)

    @Provides
    fun providesPairRepository(db: DbInterface,
                               api: BxApi): PairsRepository =
            PairRepositoryImpl(db)

    @Provides
    fun providesSyncPairRepository(db: DbInterface,
                                   api: BxApi): SyncRepository =
            SyncPairRepositoryImpl(db, api)

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
                        GlobalScope.launch {
                            dbInterface.insertSymbol(SymbolDb("THB"))
                            dbInterface.insertSymbol(SymbolDb("BTC"))
                            dbInterface.insertSymbol(SymbolDb("OMG"))
                            dbInterface.insertSymbol(SymbolDb("XRP"))
                            dbInterface.insertSymbol(SymbolDb("ETH"))
                            dbInterface.insertPair(PairDb(1, "THB", "BTC", 0.0, 0.0))
                            dbInterface.insertPair(PairDb(25, "THB", "XRP", 0.0, 0.0))
                            dbInterface.insertPair(PairDb(26, "THB", "OMG", 0.0, 0.0))
                            dbInterface.insertPair(PairDb(21, "THB", "ETH", 0.0, 0.0))
                        }
                    }
                })
                .build()
                .dbInterface()
        return dbInterface
    }
}