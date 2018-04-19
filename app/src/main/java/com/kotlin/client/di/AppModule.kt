package com.kotlin.client.di

import android.arch.persistence.room.Room
import android.content.Context
import com.kotlin.client.api.BxApi
import com.kotlin.client.database.AppDatabase
import com.kotlin.client.database.DbInterface
import com.kotlin.client.domain.GetTradesImpl
import com.kotlin.client.domain.SyncTradesImpl
import com.kotlin.client.presenter.HomeScreenPresenter
import com.kotlin.client.repository.GetTradesLocalRepository
import com.kotlin.client.repository.GetTradesRemoteRepository
import com.kotlin.client.repository.GetTradesRepository
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
    fun providesSyncTrades(@Named("local") getTradesRepositoryLocal: GetTradesRepository,
                           @Named("remote") getTradesRepositoryRemote: GetTradesRepository)
            : SyncTrades = SyncTradesImpl(getTradesRepositoryRemote, getTradesRepositoryLocal)


    @Provides
    @Singleton
    fun providesGetTrades(@Named("local") getTradesRepositoryLocal: GetTradesRepository,
                          @Named("remote") getTradesRepositoryRemote: GetTradesRepository)
            : GetTrades = GetTradesImpl(getTradesRepositoryRemote, getTradesRepositoryLocal)

    @Provides
    @Singleton
    fun providesPresenter(getTrades: GetTrades) = HomeScreenPresenter(getTrades)

    @Provides
    @Singleton
    @Named("remote")
    fun providesGetTradesRepositoryRemote(api: BxApi): GetTradesRepository =
            GetTradesRemoteRepository(api)

    @Provides
    @Singleton
    @Named("local")
    fun providesGetTradesRepositoryLocal(db: DbInterface): GetTradesRepository =
            GetTradesLocalRepository(db)

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