package com.ubertob.h4kcluster.adapter.bootstrap

import com.ubertob.h4kcluster.adapter.sumnumbers.SumNumbersId
import com.ubertob.h4kcluster.adapter.ui.UiId
import com.ubertob.h4kcluster.adapter.wordcounter.WordCounterId
import org.http4k.client.OkHttp
import org.http4k.core.HttpHandler
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.http4k.filter.DebuggingFilters
import org.http4k.server.Undertow
import org.http4k.server.asServer

object DeployableServiceDiscover : ServiceDiscovery {
    private val ports: Map<ApplicationId, Int> = //todo read port config from cloud configuration
        mapOf(
            SumNumbersId to 8091,
            WordCounterId to 8092,
            UiId to 8093
        )

    override fun provideHttpClient(id: ApplicationId): HttpHandler {
        val uri = calculateUri(id)
        return ClientFilters.SetBaseUriFrom(uri)
            .then(DebuggingFilters.PrintRequestAndResponse())
            .then(OkHttp())
            .also { println("Connected ${id.hostname} on $uri") }
    }

    private fun calculateUri(id: ApplicationId): Uri =
        ports[id]?.let {
            Uri.of("http://${id.hostname}.localhost:${it}") //todo, replace localhost with your cloud domain...
        } ?: error("Application not registered: $id")


    fun startServer(creator: (ServiceDiscovery) -> Application) {
        val app = creator(this)
        val port = ports[app.id] ?: error("Application not registered: ${app.id}")

        val debuggedApp = DebuggingFilters.PrintRequestAndResponse().then(app.handler)

        debuggedApp
            .asServer(Undertow(port))
            .start()
            .also { println("Started ${app.description} on $port") }
    }

}
