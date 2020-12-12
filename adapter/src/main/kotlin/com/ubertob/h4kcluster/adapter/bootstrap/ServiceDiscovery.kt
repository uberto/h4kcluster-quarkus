package com.ubertob.h4kcluster.adapter.bootstrap

import org.http4k.core.HttpHandler

interface ServiceDiscovery {
  fun provideHttpClient(id: ApplicationId): HttpHandler
}




