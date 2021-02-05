package com.ubertob.h4kcluster.domain

class CountWordHub {
  fun countWords(text: String): Int = text
        .split("\\s".toRegex())
        .filterNot(String::isBlank)
        .onEach { println("! $it") }
        .count()
}


