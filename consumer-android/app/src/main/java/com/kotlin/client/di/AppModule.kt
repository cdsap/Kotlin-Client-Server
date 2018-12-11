package com.kotlin.client.di

import android.app.Application
import android.content.Context
import com.kotlin.client.BuildConfig
import com.kotlin.client.database.AppDatabase
import com.kotlin.client.di.homescreen.HomeScreenComponent
import com.kotlin.client.di.pairscreen.PairScreenComponent
import com.kotlin.client.di.worker.SyncWorkerModule
import com.kotlin.client.domain.di.DomainModule
import com.kotlin.client.repository.di.RepositoryModule
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(subcomponents = [HomeScreenComponent::class, PairScreenComponent::class],
        includes = [RepositoryModule::class, DomainModule::class, SyncWorkerModule::class])
class AppModule {

    @Provides
    fun provideContext(application: Application) = application.applicationContext

    @Provides
    @Named("URL")
    fun provideUrl() = BuildConfig.URL

    @Provides
    fun providesDb(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

}