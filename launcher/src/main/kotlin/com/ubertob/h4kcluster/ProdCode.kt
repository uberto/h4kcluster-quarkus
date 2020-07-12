package com.ubertob.h4kcluster


import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status

data class InternalServiceId(val name: String)
data class ExternalServiceId(val name: String)

/**
 * Proxies requests to the App service
 */
object Proxy {
    val ID = InternalServiceId("proxy")

    operator fun invoke(appHttp: HttpHandler) = { req: Request -> appHttp(req) }
}

/**
 * This is a particular application which uses the 3rd party Reverser service
 */
object App {
    val ID = InternalServiceId("app")

    operator fun invoke(reverserHttp: HttpHandler): HttpHandler {
        val reverser = Reverser.Client(reverserHttp)
        return { _: Request -> Response(Status.OK).body(reverser.reverse("hello world")) }
    }
}

/**
 * Domain client for the 3rd party Reverser service
 */
object Reverser {
    val ID = ExternalServiceId("reverser")

    class Client(private val http: HttpHandler) {
        fun reverse(input: String) = http(Request(Method.GET, "/").body(input)).bodyString()
    }
}