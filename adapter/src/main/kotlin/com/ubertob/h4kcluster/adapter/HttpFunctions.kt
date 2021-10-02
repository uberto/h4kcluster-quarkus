package com.ubertob.h4kcluster.adapter

import org.http4k.core.Response
import org.http4k.core.Status

fun toOkResponse(bodyString: String) = Response(Status.OK).body(bodyString)
