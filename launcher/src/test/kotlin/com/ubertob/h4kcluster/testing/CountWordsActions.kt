package com.ubertob.h4kcluster.testing

import com.ubertob.pesticide.core.DdtProtocol
import com.ubertob.pesticide.core.DomainActions

interface CountWordsActions: DomainActions<DdtProtocol> {

    fun getTotalOfWords(text: String): Int
}

val allInterpreters = setOf(
    DomainOnlyWordCount(),
    HttpWordCount("localhost", 8082)
)