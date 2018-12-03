package com.kotlin.client.di

import android.app.Application
import com.kotlin.client.di.homescreen.HomeScreenComponent
import com.kotlin.client.di.pairscreen.PairScreenComponent
import com.kotlin.client.di.worker.SyncWokerModule
import com.kotlin.client.domain.di.DomainModule
import com.kotlin.client.repository.di.RepositoryModule
import dagger.Module
import dagger.Provides

@Module(subcomponents = [HomeScreenComponent::class, PairScreenComponent::class],
        includes = [RepositoryModule::class, DomainModule::class, SyncWokerModule::class])
class AppModule {

    @Provides
    fun context(application: Application) = application.applicationContext
}