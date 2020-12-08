package com.ubertob.h4kcluster.sumnumbers

import com.ubertob.h4kcluster.domain.SumNumbersHub
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes

class SumNumberHandler(val hub: SumNumbersHub) : HttpHandler {

  val routes = routes(
        "/sum/{a}/{b}" bind GET to { req: Request ->
          req.path("a")
                ?.let { a ->
                  req.path("b")
                        ?.let { b ->
                          val tot = hub.sum(a, b)
                          Response(OK).body(tot.toString())
                        }
                }
                ?: Response(NOT_FOUND).body("wrong path")
        },
        "/info" bind GET to { _: Request -> Response(OK).body("SumNumberHandler info") },
        "/bye" bind GET to { _: Request ->
          System.exit(0)
          Response(OK).body("SumNumberHandler bye")
        },
        "/" bind GET to { _: Request ->
          Response(OK).body("<html><body><h1>Hello World!  All it's working fine!</h1></body></html>")
        }
  )

  override fun invoke(request: Request): Response = routes(request)

}