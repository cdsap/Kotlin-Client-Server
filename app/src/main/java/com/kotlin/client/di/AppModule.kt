package com.kotlin.client.di

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kotlin.client.api.BxApi
import com.kotlin.client.database.AppDatabase
import com.kotlin.client.database.DbInterface
import com.kotlin.client.database.PairDb
import com.kotlin.client.database.SymbolDb
import com.kotlin.client.di.homescreen.HomeScreenComponent
import com.kotlin.client.di.homescreen.HomeScreenModule
import com.kotlin.client.di.pairscreen.PairScreenComponent
import com.kotlin.client.di.pairscreen.PairScreenModule
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

@Module(subcomponents = [HomeScreenComponent::class, PairScreenComponent::class],
        includes = [RepositoryModule::class])
class AppModule {

    @Provides
    fun context(application: Application) = application.applicationContext

    @Provides
    fun providesSyncTrades(repository: GetTradesRepository)
            : SyncTrades = SyncTradesImpl(repository)
}