package com.kotlin.server.context

import com.googlecode.objectify.Objectify
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener

class InitData : ServletContextListener {
//(val db: Objectify)
    override fun contextDestroyed(sce: ServletContextEvent?) {

    }

    override fun contextInitialized(sce: ServletContextEvent?) {
        System.out.print("dkdkdkdkdkdk")
    }

}