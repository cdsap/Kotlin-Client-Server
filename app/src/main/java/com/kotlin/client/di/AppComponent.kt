package com.kotlin.client.di

import android.app.Application
import com.kotlin.client.MainApplication
import com.kotlin.client.job.SyncWorker
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

@Component(modules = [AndroidInjectionModule::class,
    AppModule::class,
    ContributeActivityModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent


    }

    fun inject(app: MainApplication)

    fun inject(syncWorker: SyncWorker)
}


