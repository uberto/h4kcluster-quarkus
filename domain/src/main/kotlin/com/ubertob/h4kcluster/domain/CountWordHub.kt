package com.ubertob.h4kcluster.domain

class CountWordHub {
    fun countWords(text: String): Int = text.split("(\\b[^\\s]+\\b)".toRegex()).count()
}