package com.kotlin.client.di

import android.content.Context
import com.kotlin.client.homescreen.HomeScreenActivity
import com.kotlin.client.job.SyncJob
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface Injector {

    fun context(): Context

    fun inject(activity: HomeScreenActivity)

    fun inject(service: SyncJob)
}
