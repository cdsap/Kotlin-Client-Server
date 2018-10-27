package com.kotlin.client

import android.app.Application
import androidx.work.*
import com.kotlin.client.di.DaggerAppComponent
import com.kotlin.client.job.SyncWorker
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.kotlin.client.di.DaggerWorkerFactory
import dagger.android.HasActivityInjector
import java.util.concurrent.TimeUnit
import android.app.Activity
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class MainApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent
                .builder()
                .application(this).build()

        appComponent.inject(this)
        return appComponent
    }
}

//    @Inject
//    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
//
//    override fun onCreate() {
//        super.onCreate()
//        DaggerAppComponent.builder().application(this).build()
//        val configuration = Configuration.Builder()
//                .setWorkerFactory(DaggerWorkerFactory())
//                .build()
//
//        WorkManager.initialize(this, configuration)
//        val photoCheckBuilder =
//                PeriodicWorkRequest.Builder(SyncWorker::class.java, 12, TimeUnit.MINUTES)
//                        .build()
//        WorkManager.getInstance().enqueue(photoCheckBuilder)
//    }
//
//    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
//        return activityDispatchingAndroidInjector
//    }
//}