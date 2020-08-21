package com.ubertob.h4kcluster

enum class MyClusterApp(val hostname: String) {
    ui("micro-frontend"),
    wc("wordcounter-backend"),
    sn("sumnumbers-backend")
}