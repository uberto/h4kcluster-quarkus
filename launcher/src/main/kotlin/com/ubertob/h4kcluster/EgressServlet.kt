package com.ubertob.h4kcluster

import com.ubertob.h4kcluster.domain.SumNumbersHub
import com.ubertob.h4kcluster.sumnumbers.SumNumberHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.servlet.HttpHandlerServlet
import javax.servlet.Servlet
import javax.servlet.annotation.WebServlet


@WebServlet("/")
open class RestServlet: Servlet by HttpHandlerServlet(
        //here should go the egress UI
        SumNumberHandler(SumNumbersHub())
//        MyHandler()

)

class MyHandler : (Request) -> Response {
    override fun invoke(request: Request): Response {
        return Response(OK).body("<html><body><h1>Hello!!!!!!!</h1></body></html>")
    }
}
