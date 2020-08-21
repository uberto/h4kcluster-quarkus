package com.ubertob.h4kcluster.domain

class UiHub(
        val wordCounter: (String) -> Int?,
        val sumNumbers: (Int, Int) -> Int?
) {

    fun countWords(text: String): Int =
       wordCounter(text) ?: 0

}