package com.ubertob.h4kcluster.testing

import com.ubertob.h4kcluster.domain.CountWordHub
import com.ubertob.h4kcluster.domain.SumNumbersHub
import com.ubertob.h4kcluster.domain.UiHub
import com.ubertob.pesticide.core.DdtProtocol
import com.ubertob.pesticide.core.DomainOnly
import com.ubertob.pesticide.core.DomainSetUp
import com.ubertob.pesticide.core.Ready

class DomainOnlyWordCount: CountWordsInterpreter {

    override val protocol: DdtProtocol
        get() = DomainOnly

    override fun prepare(): DomainSetUp = Ready


    private val wordCountHub = CountWordHub()
    private val sumNumbersHub = SumNumbersHub()
    private val uiHub = UiHub(wordCountHub::countWords, sumNumbersHub::sum)



    override fun getTotalOfWords(text: String) = TODO("finish me")

}
