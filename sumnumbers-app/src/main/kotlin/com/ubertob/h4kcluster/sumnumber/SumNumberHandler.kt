package com.ubertob.h4kcluster.sumnumber

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import com.ubertob.h4kcluster.domain.SumNumbersHub

class SumNumberHandler(val hub: SumNumbersHub) : HttpHandler{

    val routes = routes(
                    "/sum/{a}/{b}" bind GET to { req: Request ->
                        req.path("a")
                                ?.let { a -> req.path("b")
                                        ?.let { b ->
                                            Response(OK).body(hub.sum(a, b))
                                        }
                                }
                                ?: Response(NOT_FOUND)
                    },
                    "/info" bind GET to { _: Request -> Response(OK).body("Bye from SumNumberHandler") }
            )

    override fun invoke(request: Request): Response = routes(request)

}