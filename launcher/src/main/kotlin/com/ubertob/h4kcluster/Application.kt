package com.ubertob.h4kcluster

import org.http4k.core.HttpHandler

abstract class Application<T: Enum<T>>(): AutoCloseable{
    abstract val id: T
    abstract val description: String
    abstract val handler: HttpHandler
}