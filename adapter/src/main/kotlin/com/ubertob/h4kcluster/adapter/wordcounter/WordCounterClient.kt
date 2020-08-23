package com.ubertob.h4kcluster.adapter.wordcounter

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status

class WordCounterClient(val handler: HttpHandler): (String) -> Int {
    override fun invoke(text: String): Int {

        val req = Request(Method.GET, WordCounterRoutes.count).body(text)

        val resp = handler(req)

        if (resp.status == Status.OK)
            return resp.bodyString().toInt()
        else
            return -1

    }
}