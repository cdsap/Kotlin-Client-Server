package com.kotlin.server.repository

import com.googlecode.objectify.Objectify
import com.googlecode.objectify.cmd.Saver
import com.kotlin.server.repository.database.TradeStore
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetTradesLocalRepositoryTest {
    @Mock
    lateinit var objectify: Objectify
    @Mock
    lateinit var saver: Saver

    val getTradesLocalRepository by lazy {
        GetTradesLocalRepository(objectify)
    }


    @Test
    fun testTradeStoreIsSavedInLocalRepository() {
        // Arrange
        whenever(objectify.save()).thenReturn(saver)

        // Act
        getTradesLocalRepository.save(getTrade())

        // Assert
        verify(saver).entity<TradeStore>(argThat {
            trade_id == 0L &&
                    trade_date == "1" &&
                    trade_type == "SELL" &&
                    amount == 1.0 &&
                    rate == 1.0
        })

    }

    private fun getTrade() = TradeStore(trade_id = 0L,
            trade_date = "1",
            trade_type = "SELL",
            amount = 1.0,
            rate = 1.0)
}