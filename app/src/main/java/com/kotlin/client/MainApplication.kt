package com.kotlin.client

import androidx.work.*
import com.kotlin.client.di.DaggerAppComponent
import com.kotlin.client.job.SyncWorker
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.kotlin.client.di.DaggerWorkerFactory
import java.util.concurrent.TimeUnit
import com.kotlin.core.usecases.SyncTrades
import javax.inject.Inject


class MainApplication : DaggerApplication() {

    @Inject
    lateinit var workerFactor: DaggerWorkerFactory

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent
                .builder()
                .application(this).build()
        appComponent.inject(this)
        val configuration = Configuration.Builder()
                .setWorkerFactory(workerFactor)
                .build()

        WorkManager.initialize(this, configuration)
        val workerRequest =
                PeriodicWorkRequest.Builder(SyncWorker::class.java, 15, TimeUnit.MINUTES)
                        .setConstraints(Constraints.Builder().setRequiresCharging(false)
                                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                                .build())
                        .build()
        val initRequest = OneTimeWorkRequest.Builder(SyncWorker::class.java).build()
      //  WorkManager.getInstance().enqueue(initRequest)
        //  WorkManager.getInstance().enqueue(initRequest)

        return appComponent
    }
}