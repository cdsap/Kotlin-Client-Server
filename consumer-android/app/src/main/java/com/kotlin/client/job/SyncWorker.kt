package com.kotlin.client.job

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kotlin.core.usecases.SyncTrades

class SyncWorker(context: Context,
                 workerParams: WorkerParameters,
                 private val syncTrades: SyncTrades) : Worker(context, workerParams) {

    override fun doWork(): Result {
        syncTrades.syncTrades()
        return Result.SUCCESS
    }
}