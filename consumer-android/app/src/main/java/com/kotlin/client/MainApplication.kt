package com.kotlin.client

import androidx.work.Configuration
import com.kotlin.client.di.DaggerAppComponent
import com.kotlin.client.di.worker.DaggerWorkerFactory
import com.kotlin.client.job.SyncWorker
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject


class MainApplication : DaggerApplication() {

    @Inject
    lateinit var workerFactory: DaggerWorkerFactory

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent
                .builder()
                .application(this).build()
        appComponent.inject(this)
        val configuration = Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .build()

//        WorkManager.initialize(this, configuration)
//        val workerRequest =
//                PeriodicWorkRequest.Builder(SyncWorker::class.java, 60, TimeUnit.MINUTES)
//                        .setConstraints(Constraints.Builder().setRequiresCharging(false)
//                                .setRequiredNetworkType(NetworkType.UNMETERED)
//                                .setRequiresStorageNotLow(true)
//                                .build())
//                        .build()
//        WorkManager.getInstance().enqueue(workerRequest)

        return appComponent
    }
}