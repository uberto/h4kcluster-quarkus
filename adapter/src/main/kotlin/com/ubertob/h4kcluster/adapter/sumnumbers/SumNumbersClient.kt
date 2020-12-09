package com.ubertob.h4kcluster.adapter.sumnumbers

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status

class SumNumbersClient(val handler: HttpHandler) : (Int, Int) -> Int {
  override fun invoke(a: Int, b: Int): Int {

    val req = Request(Method.GET, SumNumbersRoutes.sum(a, b))

    val resp = handler(req)

    if (resp.status == Status.OK)
      return resp.bodyString().toInt()
    else
      return -1 //in case of errors
  }
}
