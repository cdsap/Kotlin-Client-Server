package com.kotlin.server

import com.kotlin.core.usecases.SyncPairs
import javax.inject.Inject
import javax.servlet.GenericServlet
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import com.kotlin.server.di.DaggerInjector


class Init : GenericServlet() {
    init {
        DaggerInjector.builder().build().inject(this)
    }

    @Inject
    lateinit var getPairs: SyncPairs

    override fun service(req: ServletRequest?, res: ServletResponse?) {}

    override fun init() {
        super.init()
        getPairs.syncPairs()
    }
}
