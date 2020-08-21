package com.ubertob.h4kcluster

import com.ubertob.h4kcluster.MyClusterApp.*
import com.ubertob.h4kcluster.countword.CountWordHandler
import com.ubertob.h4kcluster.domain.CountWordHub
import com.ubertob.h4kcluster.domain.SumNumbersHub
import com.ubertob.h4kcluster.sumnumbers.SumNumberHandler
import com.ubertob.h4kcluster.ui.UiHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status


class LocalAppsHandler : (Request) -> Response {

    val apps = startAll()

    private fun startAll(): List<MyApplication> {

        return listOf(
                MyApplication(wc, "words counter (/count)", CountWordHandler(CountWordHub())),
                MyApplication(sn, "sum numbers (/sum/a/b)", SumNumberHandler(SumNumbersHub())),
                MyApplication(ui, ui.hostname, UiHandler())
        )

    }

    override fun invoke(request: Request): Response {

        val hostname = request.header("HOST")?.substringBefore('.').orEmpty()

        val app = apps.firstOrNull { it.id.name == hostname }

        println("application ${app?.description?:"launcher"}")

        return app?.run { handler(request) }
                ?: Response(Status.OK).body("<html><body><h1>Hello this is the launcher!</h1> <p>$hostname</p><p>$request</p></body></html>")
    }
}