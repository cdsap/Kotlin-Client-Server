package com.kotlin.client.di

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kotlin.client.api.BxApi
import com.kotlin.client.database.AppDatabase
import com.kotlin.client.database.DbInterface
import com.kotlin.client.database.PairDb
import com.kotlin.client.database.SymbolDb
import com.kotlin.client.domain.GetTradesImpl
import com.kotlin.client.domain.SyncTradesImpl
import view.homescreen.HomeScreenPresenter
import com.kotlin.client.repository.GetTradesRepository
import com.kotlin.client.repository.GetTradesRepositoryImpl
import com.kotlin.core.usecases.GetTrades
import com.kotlin.core.usecases.SyncTrades
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.launch
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    lateinit var dbInterface: DbInterface

    @Provides
    @Singleton
    fun context() = context

    @Provides
    @Singleton
    fun providesSyncTrades(repository: GetTradesRepository)
            : SyncTrades = SyncTradesImpl(repository)

    @Provides
    @Singleton
    fun providesRepository(db: DbInterface,
                           api: BxApi): GetTradesRepository =
            GetTradesRepositoryImpl(db, api)

    @Provides
    @Singleton
    fun providesGetTrades(repository: GetTradesRepository)
            : GetTrades = GetTradesImpl(repository)

    @Provides
    @Singleton
    fun providesPresenter(getTrades: GetTrades) = HomeScreenPresenter(getTrades)

    @Provides
    @Singleton
    fun providesRestApi(): BxApi = BxApi()

    @Provides
    @Singleton
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