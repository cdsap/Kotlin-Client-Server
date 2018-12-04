package com.kotlin.server.repository.api.patch

import okhttp3.*
import okhttp3.internal.http.HttpMethod
import okio.Buffer
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

class CallWrapper(private val request: Request, private var executed: Boolean = false,
                  private var cancelled: Boolean = false) : Call {

    override fun enqueue(responseCallback: Callback?) {}

    override fun isExecuted() = executed

    override fun clone() = CallWrapper(request)

    override fun isCanceled() = cancelled

    override fun cancel() {
        cancelled = true
    }

    override fun request() = request

    override fun execute(): Response {
        synchronized(this) {
            if (executed) {
                throw IllegalStateException("Already Executed")
            }
            executed = true
        }
        if ("GET".equals(request.method(), ignoreCase = true) ||
                "DELETE".equals(request.method(), ignoreCase = true) ||
                "POST".equals(request.method(), ignoreCase = true) ||
                "PATCH".equals(request.method(), ignoreCase = true) ||
                "PUT".equals(request.method(), ignoreCase = true)) {

            val url = URL(request.url().url().toString())

            val con = url.openConnection() as HttpURLConnection
            con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");

            if ("PATCH" == request.method()) {
                con.setRequestProperty("X-HTTP-Method-Override", "PATCH")
                con.requestMethod = "POST"
            } else {
                con.requestMethod = request.method()
            }

            setHeaders(request, url, con)

            // Send body if we're required to do so.
            if (HttpMethod.requiresRequestBody(request.method()) && request.body()!!.contentLength() > 0) {

                val payload = Buffer()
                request.body()!!.writeTo(payload)

                con.doOutput = true
                val wr = DataOutputStream(con.outputStream)
                wr.write(payload.readByteArray())
                wr.flush()
                wr.close()
            }

            val builder = parseResponse(con)
            return builder.build()

        } else {
            throw RuntimeException("Unsupported HTTP method : " + request.method())

        }
    }

    private fun setHeaders(request: Request, url: URL, con: URLConnection) {
        val headers = request.headers()
        for (header in headers.names()) {
            con.setRequestProperty(header, headers.get(header))
        }

        // HttpsUrlConnection isn't supported on App Engine, so add a new Header to fix that.
        if (request.isHttps) {
            var port = url.port
            if (port == -1) {
                port = 443
            }
            con.setRequestProperty("Host", url.host + ":" + port)
        }
    }

    @Throws(IOException::class)
    private fun parseResponse(connection: HttpURLConnection): Response.Builder {
        val builder = Response.Builder()
        builder.request(request())

        builder.protocol(Protocol.HTTP_1_1)
        builder.code(connection.responseCode)
        builder.message(connection.responseMessage.toString())

        val `in` = connection.inputStream
        if (`in` != null) {
            `in`.use {
                val bis = BufferedInputStream(it)
                val buffer = ByteArray(8192)

                val baos = ByteArrayOutputStream()

                var len = bis.read(buffer)
                while (len != -1) {
                    baos.write(buffer, 0, len)
                    len = bis.read(buffer)
                }
                builder.body(ResponseBody.create(MediaType.parse(connection.contentType), baos.toByteArray()))
            }
        } else {
            builder.body(ResponseBody.create(null, 0, Buffer()))
        }

        return builder
    }
}