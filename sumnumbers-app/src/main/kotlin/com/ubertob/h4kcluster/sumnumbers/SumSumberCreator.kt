package com.ubertob.h4kcluster.sumnumbers

import com.ubertob.h4kcluster.adapter.bootstrap.Application
import com.ubertob.h4kcluster.adapter.bootstrap.ServiceDiscovery
import com.ubertob.h4kcluster.adapter.sumnumbers.SumNumbersId
import com.ubertob.h4kcluster.domain.SumNumbersHub

object SumSumberCreator : (ServiceDiscovery) -> Application {

    override fun invoke(sd: ServiceDiscovery): Application {
        val hub = SumNumbersHub()

        return Application(SumNumbersId, "sum numbers (/sum/a/b)", SumNumberHandler(hub))
    }

}