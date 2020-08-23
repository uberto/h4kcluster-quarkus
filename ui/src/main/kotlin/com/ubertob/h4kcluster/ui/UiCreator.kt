package com.ubertob.h4kcluster.ui

import com.ubertob.h4kcluster.adapter.bootstrap.Application
import com.ubertob.h4kcluster.adapter.bootstrap.ServiceDiscovery
import com.ubertob.h4kcluster.adapter.sumnumbers.SumNumbersClient
import com.ubertob.h4kcluster.adapter.sumnumbers.SumNumbersId
import com.ubertob.h4kcluster.adapter.ui.UiId
import com.ubertob.h4kcluster.adapter.wordcounter.WordCounterClient
import com.ubertob.h4kcluster.adapter.wordcounter.WordCounterId
import com.ubertob.h4kcluster.domain.UiHub

object UiCreator : (ServiceDiscovery) -> Application {

    override fun invoke(sd: ServiceDiscovery): Application {

        val wcClient = WordCounterClient(sd.provideHttpClient(WordCounterId))
        val snClient = SumNumbersClient(sd.provideHttpClient(SumNumbersId))
        val hub = UiHub(wcClient, snClient)

        return Application(UiId, "front-end", UiHandler(hub))
    }

}