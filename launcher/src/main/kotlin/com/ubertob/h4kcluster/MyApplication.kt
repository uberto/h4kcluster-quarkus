package com.ubertob.h4kcluster

import org.http4k.core.HttpHandler

data class MyApplication(
        override val id: MyClusterApp,
        override val description: String,
        override val handler: HttpHandler
): Application<MyClusterApp>(){

    override fun close() {
        TODO("Not yet implemented")
    }

}