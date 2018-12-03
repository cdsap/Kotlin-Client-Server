package com.kotlin.client.di.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.kotlin.core.usecases.SyncTrades
import java.lang.reflect.Constructor

class DaggerWorkerFactory(val syncTrades: SyncTrades) : WorkerFactory() {
    override fun createWorker(appContext: Context, workerClassName: String, workerParameters: WorkerParameters): ListenableWorker? {

        val workerClass: Class<out Worker> = Class.forName(workerClassName).asSubclass(Worker::class.java)
        val contructor: Constructor<out Worker> = workerClass.getDeclaredConstructor(Context::class.java, WorkerParameters::class.java, SyncTrades::class.java)
        val instance = contructor.newInstance(appContext, workerParameters, syncTrades)
        return instance
    }

}