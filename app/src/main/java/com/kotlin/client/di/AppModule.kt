package com.kotlin.client.di

import android.app.Application
import com.kotlin.client.di.homescreen.HomeScreenComponent
import com.kotlin.client.di.pairscreen.PairScreenComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [HomeScreenComponent::class, PairScreenComponent::class],
        includes = [RepositoryModule::class, SyncWokerModule::class])
class AppModule {

    @Provides
    fun context(application: Application) = application.applicationContext
}