package com.ubertob.h4kcluster.countword

import com.ubertob.h4kcluster.adapter.bootstrap.Application
import com.ubertob.h4kcluster.adapter.bootstrap.ServiceDiscovery
import com.ubertob.h4kcluster.adapter.wordcounter.WordCounterId
import com.ubertob.h4kcluster.domain.CountWordHub

object CountWordCreator: (ServiceDiscovery) -> Application {
    
    override fun invoke(sd: ServiceDiscovery): Application {

        val hub = CountWordHub()

        val handler = CountWordHandler(hub)

        return Application(WordCounterId, "words counter (/count)", handler)
    }

}