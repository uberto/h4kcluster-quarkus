package com.ubertob.h4kcluster

import org.http4k.core.HttpHandler

data class Application(val name: String, val handler: HttpHandler)