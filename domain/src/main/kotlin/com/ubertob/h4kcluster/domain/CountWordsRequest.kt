package com.ubertob.h4kcluster.domain

data class CountWordsRequest(val id:Long, val user: String, val useDictionary: Boolean, val lines: List<String>)