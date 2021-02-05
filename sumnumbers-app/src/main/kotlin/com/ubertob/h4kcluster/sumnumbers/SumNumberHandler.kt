package com.ubertob.h4kcluster.sumnumbers

import com.ubertob.h4kcluster.domain.SumNumbersHub
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes

class SumNumberHandler(val hub: SumNumbersHub) : HttpHandler {

  val routes = routes(
        "/sum/{a}/{b}" bind GET to { req: Request ->
          val a = req.path("a")
          val b = req.path("b")
          if (a != null && b != null) {
            val tot = hub.sum(a.toInt(), b.toInt())
            Response(OK).body(tot.toString())
          } else
            Response(BAD_REQUEST).body("wrong request: ${req.uri}")
        },
        "/" bind GET to { _: Request ->
          Response(OK).body("<html><body><h1>This is SumNumbers</h1></body></html>")
        }
  )

  override fun invoke(request: Request): Response = routes(request)

}