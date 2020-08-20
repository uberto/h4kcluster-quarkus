package com.ubertob.h4kcluster

import com.ubertob.h4kcluster.countword.CountWordHandler
import com.ubertob.h4kcluster.domain.CountWordHub
import com.ubertob.h4kcluster.domain.SumNumbersHub
import com.ubertob.h4kcluster.sumnumbers.SumNumberHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.servlet.HttpHandlerServlet
import javax.servlet.Servlet
import javax.servlet.annotation.WebServlet


@WebServlet("/")
open class RestServlet : Servlet by HttpHandlerServlet(
        //here should go the egress UI
//        com.ubertob.h4kcluster.countword.SumNumberHandler(SumNumbersHub())
        LocalAppsHandler()

)

class LocalAppsHandler : (Request) -> Response {

    val apps = startAll()

    private fun startAll(): List<Application> {

        return listOf(
                Application("wc", "words counter (/count)", CountWordHandler(CountWordHub())),
                Application("sn",  "sum numbers (/sum/a/b)", SumNumberHandler(SumNumbersHub()))
        )

    }

    override fun invoke(request: Request): Response {

        val hostname = request.header("HOST")?.substringBefore('.').orEmpty()

        val app = apps.firstOrNull { it.name == hostname }

        println("application ${app?.description?:"launcher"}")

        return app?.run { handler(request) }
                ?: Response(OK).body("<html><body><h1>Hello this is the launcher!</h1> <p>$hostname</p><p>$request</p></body></html>")
    }
}


