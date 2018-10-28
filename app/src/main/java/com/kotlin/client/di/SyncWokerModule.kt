package com.kotlin.client.di

import com.kotlin.client.domain.SyncTradesImpl
import com.kotlin.client.repository.GetTradesRepository
import com.kotlin.core.usecases.SyncTrades
import dagger.Module
import dagger.Provides

@Module
class SyncWokerModule {
    @Provides
    fun providesWorkerFactory(syncTrades: SyncTrades)
            : DaggerWorkerFactory = DaggerWorkerFactory(syncTrades)

    @Provides
    fun provideSyncTrades(repository: GetTradesRepository): SyncTrades =
            SyncTradesImpl(repository)
}