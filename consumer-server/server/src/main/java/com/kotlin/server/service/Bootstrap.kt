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
        ObjectifyService.init()

        System.out.println("2111")
        ObjectifyService.register(TradeStore::class.java)
        System.out.println("211vvvvvvv1")

        ObjectifyService.register(SymbolStore::class.java)
        System.out.println("211ww1")
        ObjectifyService.register(PairStore::class.java)
        System.out.println("211ssss1")
        initData()
        System.out.println("2111")
    }

    override fun contextDestroyed(sce: ServletContextEvent?) {
        //      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun initData() {
        //if (ObjectifyService.ofy().load().type(SymbolStore::class.java)
        //                .count() == 0) {
        System.out.println("21www11")


        ObjectifyService.run {

ofy().save().entity(SymbolStore("THB"))

      //      ofy().factory()
//                    Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("ETH").safe())))
        }

    }


}