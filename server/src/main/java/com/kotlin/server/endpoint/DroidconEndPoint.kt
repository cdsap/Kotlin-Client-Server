package com.kotlin.server.endpoint

import com.kotlin.server.service.GetTradesService
import com.kotlin.server.service.SyncService
import com.google.api.server.spi.config.Api
import com.google.api.server.spi.config.ApiMethod
import javax.inject.Inject
import com.kotlin.server.di.DaggerInjector
import javax.inject.Named

@Api(name = "droidcon", version = "v1")
class DroidconEndPoint {
    init {
        DaggerInjector.builder().build().inject(this)
    }

    @Inject
    lateinit var syncService: SyncService

    @Inject
    lateinit var getTradesService: GetTradesService


    @ApiMethod(name = "sync",
            httpMethod = ApiMethod.HttpMethod.GET,
            path = "sync/")
    fun sync() = syncService.sync()

    @ApiMethod(name = "trades",
            httpMethod = ApiMethod.HttpMethod.GET,
            path = "trades/{pair}")
    fun getTrades(@Named("pair") id: String) = getTradesService.getTrades(id)

    @ApiMethod(name = "trades",
            httpMethod = ApiMethod.HttpMethod.GET,
            path = "trades/")
    fun getTradesByPair() = getTradesService.getTradesByPair()
}

