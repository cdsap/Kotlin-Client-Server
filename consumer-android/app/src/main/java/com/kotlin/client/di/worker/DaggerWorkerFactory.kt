package com.kotlin.client.di.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.kotlin.core.usecases.GetPairs
import java.lang.reflect.Constructor

class DaggerWorkerFactory(private val syncTrades: GetPairs) : WorkerFactory() {
    override fun createWorker(appContext: Context, workerClassName: String,
                              workerParameters: WorkerParameters): ListenableWorker? {

        val workerClass: Class<out Worker> = Class.forName(workerClassName).asSubclass(Worker::class.java)
        val constructor: Constructor<out Worker> = workerClass.getDeclaredConstructor(Context::class.java,
                WorkerParameters::class.java, GetPairs::class.java)
        return constructor.newInstance(appContext, workerParameters, syncTrades)
    }

}