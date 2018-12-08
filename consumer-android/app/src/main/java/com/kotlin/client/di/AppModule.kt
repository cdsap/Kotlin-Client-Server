package com.kotlin.client.di

import android.app.Application
import com.kotlin.client.BuildConfig
import com.kotlin.client.di.homescreen.HomeScreenComponent
import com.kotlin.client.di.pairscreen.PairScreenComponent
import com.kotlin.client.di.worker.SyncWokerModule
import com.kotlin.client.repository.di.RepositoryModule
import com.kotlin.core.DomainModule
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(subcomponents = [HomeScreenComponent::class, PairScreenComponent::class],
        includes = [RepositoryModule::class, DomainModule::class, SyncWokerModule::class])
class AppModule {

    @Provides
    fun context(application: Application) = application.applicationContext

    @Provides
    @Named("URL")
    fun providesUrl() = BuildConfig.URL
}