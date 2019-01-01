package com.kotlin.server.cron

import com.kotlin.server.endpoint.EndPoint.Companion.BASE_URL
import com.kotlin.server.endpoint.EndPoint.Companion.ENDPOINT
import com.kotlin.server.endpoint.EndPoint.Companion.VERSION
import java.net.HttpURLConnection
import java.net.URL
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class CronTrades : HttpServlet() {

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val url = URL(URL)
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.inputStream
        conn.disconnect()
    }

    companion object {
        const val URL = "$BASE_URL/$ENDPOINT/$VERSION/syncTrades/"
    }
}
