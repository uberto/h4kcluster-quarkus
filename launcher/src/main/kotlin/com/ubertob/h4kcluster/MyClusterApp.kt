package com.ubertob.h4kcluster

enum class MyClusterApp(hostname: String) {
    ui("micro-frontend"), wc("wordcounter-backend"), sn("sumnumbers-backend")
}