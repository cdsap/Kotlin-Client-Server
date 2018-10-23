package com.kotlin.client

import androidx.work.*
import com.kotlin.client.di.DaggerAppComponent
import com.kotlin.client.job.SyncWorker
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.kotlin.client.di.DaggerWorkerFactory
import java.util.concurrent.TimeUnit


class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)

        val configuration = Configuration.Builder()
                .setWorkerFactory(DaggerWorkerFactory())
                .build()

        WorkManager.initialize(this, configuration)
        val photoCheckBuilder =
                PeriodicWorkRequest.Builder(SyncWorker::class.java, 12, TimeUnit.MINUTES)
                        .build()
        WorkManager.getInstance().enqueue(photoCheckBuilder)
        return appComponent
    }
}