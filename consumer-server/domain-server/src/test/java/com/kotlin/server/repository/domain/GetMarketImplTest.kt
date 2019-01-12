package com.kotlin.server.repository.domain

import com.kotlin.core.domain.entities.repository.SyncRepository
import com.nhaarman.mockitokotlin2.*
import io.kotlintest.specs.BehaviorSpec

class GetMarketImplTest : BehaviorSpec({
    given("GetMarket Implementation") {

        `when`("I want to sync Market ") {
            val syncRepository = mock<SyncRepository> { }
            val getMarket = GetMarketImpl(syncRepository)
            whenever(syncRepository.get()).thenReturn(emptyList())
            getMarket.get()
            then("I should call repository") {
                verify(syncRepository).get()
            }
        }
    }
})