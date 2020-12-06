package com.ubertob.h4kcluster.countword

import com.ubertob.h4kcluster.adapter.bootstrap.DeployableServiceDiscover

fun main(){
    DeployableServiceDiscover.startServer(CountWordCreator)

}