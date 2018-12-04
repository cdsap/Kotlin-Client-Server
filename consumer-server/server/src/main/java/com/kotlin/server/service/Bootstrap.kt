package com.kotlin.server.service

import com.googlecode.objectify.Objectify
import com.googlecode.objectify.ObjectifyService
import com.googlecode.objectify.ObjectifyService.ofy
import com.googlecode.objectify.Ref
import com.kotlin.server.repository.database.PairStore
import com.kotlin.server.repository.database.SymbolStore
import com.kotlin.server.repository.database.TradeStore
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener


class Bootstrap : ServletContextListener {

    override fun contextInitialized(sce: ServletContextEvent?) {

    }

    override fun contextDestroyed(sce: ServletContextEvent?) {
        //      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}