package com.ubertob.h4kcluster.testing

import com.ubertob.pesticide.core.DdtActor
import strikt.api.expectThat
import strikt.assertions.isEqualTo

data class UserActor(override val name: String) : DdtActor<CountWordsInterpreter>() {
    fun `verify that the number of words is $`(expectedWords: Int) = step(expectedWords) {
        val tow = getTotalOfWords()
        expectThat(tow).isEqualTo(expectedWords)
    }

    fun `insert the text`(text: String) = step {
        sendATextToBeCounted(text)
    }

}
