package com.kotlin.client.di.worker

import com.kotlin.core.usecases.GetPairs
import dagger.Module
import dagger.Provides

@Module
class SyncWorkerModule {
    @Provides
    fun providesWorkerFactory(syncTrades: GetPairs)
            : DaggerWorkerFactory = DaggerWorkerFactory(syncTrades)

}