package com.ubertob.h4kcluster.countword

import com.ubertob.h4kcluster.adapter.toOkResponse
import com.ubertob.h4kcluster.domain.CountWordHub
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.routing.bind
import org.http4k.routing.routes

class CountWordHandler(val hub: CountWordHub) : HttpHandler {

    val routes = routes(
        "/count" bind POST to { req: Request ->
            req.bodyString()
                .let(hub::countWords)
                .toString()
                .let(::toOkResponse)
        },
        "/" bind GET to { _: Request ->
            toOkResponse("<html><body><h1>This is CountWords</h1></body></html>")
        }
    )

    override fun invoke(request: Request): Response = routes(request)

}

//example
//curl -i -X POST -d 'The quick brown fox jumps over the lazy dog' http://localhost:8082/count