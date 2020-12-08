package com.ubertob.h4kcluster.domain

class CountWordHub {
  fun countWords(text: String): Int = text
        .split("[\\pP\\s&&[^']]+".toRegex())
        .onEach { println("! $it") }
        .count()
}


