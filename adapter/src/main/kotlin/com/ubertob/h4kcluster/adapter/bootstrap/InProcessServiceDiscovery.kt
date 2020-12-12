package com.ubertob.h4kcluster.adapter.bootstrap

import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status
import java.util.concurrent.atomic.AtomicReference

class InProcessServiceDiscovery : ServiceDiscovery {
  private val applications: AtomicReference<Map<ApplicationId, Application>> = AtomicReference(emptyMap())

  override fun provideHttpClient(id: ApplicationId): HttpHandler {
    return applications.get()[id]?.handler ?: { Response(Status.BAD_GATEWAY).body("application unknown $id") }
  }

  fun register(creator: (ServiceDiscovery) -> Application) {
    applications.getAndUpdate { it + creator(this).toMapEntry() }
  }

  fun findByHostname(hostname: String): Application? =
        applications.get().values.firstOrNull { it.id.hostname == hostname }

}

private fun Application.toMapEntry(): Pair<ApplicationId, Application> =
      id to this
