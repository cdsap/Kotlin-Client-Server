package com.kotlin.client.di

import android.arch.persistence.room.Room
import android.content.Context
import com.kotlin.client.api.BxApi
import com.kotlin.client.database.AppDatabase
import com.kotlin.client.database.DbInterface
import com.kotlin.client.domain.GetTradesImpl
import com.kotlin.client.domain.SyncTradesImpl
import com.kotlin.client.presenter.HomeScreenPresenter
import com.kotlin.client.repository.GetTradesRepository
import com.kotlin.client.repository.GetTradesRepositoryImpl
import com.kotlin.core.usecases.GetTrades
import com.kotlin.core.usecases.SyncTrades
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

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
    fun providesDbInterface(context: Context) =
            Room.databaseBuilder(context,
                    AppDatabase::class.java,
                    "bx.db")
                    .build()
                    .dbInterface()
}