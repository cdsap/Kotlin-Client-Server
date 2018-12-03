package com.kotlin.server.service

import com.googlecode.objectify.ObjectifyService
import com.googlecode.objectify.Ref
import com.kotlin.server.repository.database.PairStore
import com.kotlin.server.repository.database.SymbolStore
import com.kotlin.server.repository.database.TradeStore
import javax.servlet.Servlet
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.http.HttpServlet


class Bootstrap :  HttpServlet() {

    override fun init() {
        super.init()
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

    private fun initData() {
        //if (ObjectifyService.ofy().load().type(SymbolStore::class.java)
        //                .count() == 0) {
        System.out.println("21www11")

        ObjectifyService.ofy().save().entity(SymbolStore("THB")).now()
        ObjectifyService.ofy().save().entity(SymbolStore("BTC")).now()
        ObjectifyService.ofy().save().entity(SymbolStore("OMG")).now()
        ObjectifyService.ofy().save().entity(SymbolStore("XRP")).now()
        ObjectifyService.ofy().save().entity(SymbolStore("ETH")).now()

        ObjectifyService.ofy().save().entity(PairStore(
                1,
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("THB").safe()),
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("BTC").safe())))

        ObjectifyService.ofy().save().entity(PairStore(
                25,
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("THB").safe()),
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("XRP").safe())))

        ObjectifyService.ofy().save().entity(PairStore(
                26,
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("THB").safe()),
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("OMG").safe())))

        ObjectifyService.ofy().save().entity(PairStore(
                21,
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("THB").safe()),
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("ETH").safe())))

    }


}