package com.ubertob.h4kcluster.testing

import com.ubertob.pesticide.core.DdtProtocol
import com.ubertob.pesticide.core.DomainOnly
import com.ubertob.pesticide.core.DomainSetUp
import com.ubertob.pesticide.core.Ready

class DomainOnlyWordCount: CountWordsInterpreter {

    override val protocol: DdtProtocol
        get() = DomainOnly

    override fun prepare(): DomainSetUp = Ready



    override fun sendATextToBeCounted(text: String) {
        TODO("Not yet implemented")
    }

    override fun getTotalOfWords(): Int {
        TODO("Not yet implemented")
    }


}
