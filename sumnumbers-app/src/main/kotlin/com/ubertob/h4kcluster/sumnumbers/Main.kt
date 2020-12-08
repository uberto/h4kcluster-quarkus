package com.ubertob.h4kcluster.sumnumbers

import com.ubertob.h4kcluster.adapter.bootstrap.DeployableServiceDiscover

fun main() {
  DeployableServiceDiscover.startServer(SumSumberCreator)

}