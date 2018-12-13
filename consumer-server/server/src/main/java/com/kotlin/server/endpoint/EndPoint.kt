package com.kotlin.server.endpoint

import com.kotlin.server.service.GetTradesService
import com.kotlin.server.service.SyncTradesService
import com.google.api.server.spi.config.Api
import com.google.api.server.spi.config.ApiMethod
import javax.inject.Inject
import com.kotlin.server.di.DaggerInjector
import com.kotlin.server.service.SyncPairs
import javax.inject.Named

@Api(name = "api", version = "v1")
class EndPoint {
    init {
        DaggerInjector.builder().build().inject(this)
    }

    @Inject
    lateinit var syncService: SyncTradesService

    @Inject
    lateinit var syncPairs: SyncPairs

    @Inject
    lateinit var getTradesService: GetTradesService

    @ApiMethod(name = "syncTrades",
            httpMethod = ApiMethod.HttpMethod.GET,
            path = "syncTrades/")
    fun sync() = syncService.sync()

    @ApiMethod(name = "syncPairs",
            httpMethod = ApiMethod.HttpMethod.GET,
            path = "syncPairs/")
    fun sync2() = syncPairs.sync()


    @ApiMethod(name = "trades",
            httpMethod = ApiMethod.HttpMethod.GET,
            path = "trades/{pair}")
    fun getTrades(@Named("pair") id: String) = getTradesService.getTrades(id)

    companion object {
        const val URL = ""
        const val VERSION = "2"
        const val ENDPOINT = ""

    }
}

