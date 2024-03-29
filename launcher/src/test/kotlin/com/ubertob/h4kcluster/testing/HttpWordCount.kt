package com.ubertob.h4kcluster.testing

import com.ubertob.h4kcluster.LocalAppsHandler
import com.ubertob.pesticide.core.*
import org.http4k.client.ApacheClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.body.form
import org.http4k.server.Http4kServer
import org.http4k.server.Jetty
import org.http4k.server.asServer

class HttpWordCount(val host: String, val port: Int) : CountWordsActions {
    override val protocol: DdtProtocol
        get() = Http("$host:$port")

    val client = ApacheClient()

    var server: Http4kServer? = null

    override fun prepare(): DomainSetUp = try {

        server = LocalAppsHandler()
            .asServer(Jetty(port))
            .start()
            .also { println("Started DDT http on $port") }

        Ready
    } catch (t: Throwable) {
        NotReady(t.toString())
    }

    override fun tearDown(): DdtActions<DdtProtocol> =
        apply {
            server?.stop()
        }

    private fun uri(path: String) = "http://$host:$port/$path"

    fun countWordsRequest(text: String) = Request(Method.POST, uri("/submit")).form("words", text)

    override fun getTotalOfWords(text: String): Int {
        val countWordsRequest = countWordsRequest(text)
        val resp = client(countWordsRequest)

        println(resp)
        return 42

    }


}

