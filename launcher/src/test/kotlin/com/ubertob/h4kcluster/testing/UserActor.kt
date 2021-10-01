package com.ubertob.h4kcluster.testing

import com.ubertob.pesticide.core.DdtActorWithContext

data class UserActor(override val name: String) : DdtActorWithContext<CountWordsActions, String>() {

    fun `verify that the number of words is $`(expectedWords: Int) = step(expectedWords) { ctx ->
        val text = ctx.get()


        TODO("finish me")
    }

    fun `can insert the text`(text: String) = step { ctx ->
        ctx.store(text)
    }

}
