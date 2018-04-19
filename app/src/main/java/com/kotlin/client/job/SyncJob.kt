package com.kotlin.client.job

import android.app.job.JobParameters
import android.app.job.JobService
import com.kotlin.client.di.AppModule
import com.kotlin.client.di.DaggerInjector
import com.kotlin.core.usecases.SyncTrades
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class SyncJob : JobService() {
    init {
        DaggerInjector.builder()
                .appModule(AppModule(this))
                .build()
                .inject(this)
    }

    @Inject
    lateinit var syncTrades: SyncTrades

    override fun onStartJob(params: JobParameters): Boolean {
        launch(CommonPool) { syncTrades.syncTrades(BTC) }
        jobFinished(params, false)
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return false
    }

    companion object {
        const val BTC = 1L
    }
}