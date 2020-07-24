package com.ubertob.h4kcluster.countword

import com.ubertob.h4kcluster.domain.CountWordHub
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes

class CountWordHandler(val hub: CountWordHub) : HttpHandler {

    val routes = routes(
            "/count" bind GET to { req: Request ->
                Response(OK).body("the number of words is 42")
            }
    )

    override fun invoke(request: Request): Response = routes(request)

}