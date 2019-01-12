package com.kotlin.core.domain.entities.usecases.impl;

import com.kotlin.core.domain.entities.PairSymbol
import com.kotlin.core.domain.entities.repository.PairsRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotlintest.specs.BehaviorSpec

class GetPairsImplTest : BehaviorSpec({
    given("GetPairs Implementation") {

        `when`("There are no Pairs ") {
            val pairRepository = mock<PairsRepository> { }
            val getPairs = GetPairsImpl(pairRepository)
            whenever(pairRepository.getPairs()).thenReturn(emptyList())
            getPairs.get()
            then("I should sync Pairs") {
                verify(pairRepository).syncPairs()
            }
        }
        `when`("There are Pairs") {
            val pairRepository = mock<PairsRepository> { }
            val getPairs = GetPairsImpl(pairRepository)
            whenever(pairRepository.getPairs()).thenReturn(listOf(PairSymbol(1, "BTC", "EUR",
                    0.0, 0.0)))
            getPairs.get()
            then("I shouldn't sync Pairs") {
                verify(pairRepository, never()).syncPairs()
            }
        }
    }
})