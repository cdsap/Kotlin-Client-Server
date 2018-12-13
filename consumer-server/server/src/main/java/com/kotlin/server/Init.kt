package com.kotlin.server

import com.kotlin.core.usecases.GetPairs
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
    lateinit var getPairs: GetPairs

    override fun service(req: ServletRequest?, res: ServletResponse?) {}

    override fun init() {
        super.init()
        getPairs.sync()
    }
}
