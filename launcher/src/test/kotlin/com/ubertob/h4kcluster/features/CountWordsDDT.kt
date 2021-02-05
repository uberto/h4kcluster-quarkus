package com.ubertob.h4kcluster.features

import com.ubertob.h4kcluster.testing.CountWordsInterpreter
import com.ubertob.h4kcluster.testing.UserActor
import com.ubertob.h4kcluster.testing.allInterpreters
import com.ubertob.pesticide.core.DDT
import com.ubertob.pesticide.core.DomainDrivenTest
import java.time.LocalDate

class CountWordsDDT : DomainDrivenTest<CountWordsInterpreter>(allInterpreters) {

    val wilmaTheWriter by NamedActor(::UserActor)
    val tomTheTwitter by NamedActor(::UserActor)


    @DDT
    fun `wilma can see how long is her new novel`() = ddtScenario {

        val wilmaNovel = "It was a dark and stormy night"
        withoutSetting atRise play(
            wilmaTheWriter.`can insert the text`(wilmaNovel),
            wilmaTheWriter.`verify that the number of words is $`(7)
        ).wip(LocalDate.of(2021, 3, 1), "Working on it!")
    }


}