package com.kotlin.client.job

import android.content.Context
import androidx.work.Result
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kotlin.core.usecases.GetPairs

class SyncWorker(context: Context,
                 workerParams: WorkerParameters,
                 private val syncTrades: GetPairs) : Worker(context, workerParams) {

    override fun doWork(): Result {
        syncTrades.sync()
        return Result.success()
    }
}