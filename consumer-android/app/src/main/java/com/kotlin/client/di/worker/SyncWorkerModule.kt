package com.kotlin.client.di.worker

import com.kotlin.core.domain.entities.usecases.GetPairs
import dagger.Module
import dagger.Provides

@Module
class SyncWorkerModule {
    @Provides
    fun providesWorkerFactory(getPairs: GetPairs)
            : DaggerWorkerFactory = DaggerWorkerFactory(getPairs)

}