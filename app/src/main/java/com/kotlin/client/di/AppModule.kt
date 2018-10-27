package com.kotlin.client.di

import android.app.Application
import com.kotlin.client.di.homescreen.HomeScreenComponent
import com.kotlin.client.di.pairscreen.PairScreenComponent
import com.kotlin.client.domain.SyncTradesImpl
import com.kotlin.client.repository.GetTradesRepository
import com.kotlin.core.usecases.SyncTrades
import dagger.Module
import dagger.Provides

@Module(subcomponents = [HomeScreenComponent::class, PairScreenComponent::class],
        includes = [RepositoryModule::class])
class AppModule {

    @Provides
    fun context(application: Application) = application.applicationContext

    @Provides
    fun providesWorkerFactory(syncTrades: SyncTrades)
            : DaggerWorkerFactory = DaggerWorkerFactory(syncTrades)

    @Provides
    fun provideSyncTrades(repository: GetTradesRepository): SyncTrades =
            SyncTradesImpl(repository)
}