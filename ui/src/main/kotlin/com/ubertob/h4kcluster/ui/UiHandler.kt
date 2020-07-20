package com.ubertob.h4kcluster.ui

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK


class UiHandler() : HttpHandler {
    override fun invoke(req: Request): Response  = Response(OK).body("This is UI handler")
}