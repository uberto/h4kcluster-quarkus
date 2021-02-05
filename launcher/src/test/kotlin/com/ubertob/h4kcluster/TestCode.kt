package com.ubertob.h4kcluster



import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status


fun FakeReverserApp(): HttpHandler = { req: Request -> Response(Status.OK).body(req.bodyString().reversed()) }
