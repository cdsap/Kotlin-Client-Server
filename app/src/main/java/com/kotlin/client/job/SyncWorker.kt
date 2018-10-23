package com.kotlin.client.job

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kotlin.core.usecases.SyncTrades


class SyncWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    lateinit var syncTrades: SyncTrades

    constructor(context: Context, workerParams: WorkerParameters, syncTrades: SyncTrades) : this(context, workerParams) {
        this.syncTrades = syncTrades
    }

    override fun doWork(): Result {
        syncTrades.syncTrades(1L)
        return Result.SUCCESS
    }
}