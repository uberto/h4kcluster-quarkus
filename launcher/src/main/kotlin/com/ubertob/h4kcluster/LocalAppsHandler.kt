package com.ubertob.h4kcluster

import com.ubertob.h4kcluster.adapter.bootstrap.InProcessServiceDiscovery
import com.ubertob.h4kcluster.countword.CountWordCreator
import com.ubertob.h4kcluster.sumnumbers.SumSumberCreator
import com.ubertob.h4kcluster.ui.UiCreator
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status


class LocalAppsHandler : (Request) -> Response {

  val serviceDiscovery = InProcessServiceDiscovery()

  init {
    listOf(
          CountWordCreator,
          SumSumberCreator,
          UiCreator
    ).forEach { serviceDiscovery.register(it) }
  }

  override fun invoke(request: Request): Response {

    val hostname = request.header("HOST")?.substringBefore('.').orEmpty()

    val app = serviceDiscovery.findByHostname(hostname)

    println("incoming request for ${app?.description ?: "launcher"}")

    return app?.run { handler(request) }
          ?: Response(Status.OK).body("<html><body><h1>Hello this is the launcher!</h1> <p>$hostname</p><p>$request</p></body></html>")
  }
}