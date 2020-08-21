package com.ubertob.h4kcluster.ui

import com.ubertob.h4kcluster.adapter.bootstrap.Application
import com.ubertob.h4kcluster.adapter.bootstrap.ServiceDiscovery
import com.ubertob.h4kcluster.adapter.ui.UiId
import com.ubertob.h4kcluster.adapter.wordcounter.WordCounterId
import com.ubertob.h4kcluster.domain.CountWordHub
import com.ubertob.h4kcluster.domain.UiHub

object UiCreator: (ServiceDiscovery) -> Application {

    override fun invoke(sd: ServiceDiscovery): Application {

        val hub = UiHub(
                { it.split(" ").size },
                { a, b -> a+b }
        )

        return Application(UiId, "front-end for user input", UiHandler(hub))
    }

}