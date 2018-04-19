package com.kotlin.server.repository

import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import com.kotlin.server.database.TradeStore
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetTradesRemoteRepositoryTest {
    @Mock
    lateinit var api: Api

    val getTradesRemoteRepository by lazy {
        GetTradesRemoteRepository(api)
    }

    @Test(expected = NotImplementedError::class)
    fun testSavingInRemoteRepositoryThrowsException() {
        // Act
        getTradesRemoteRepository.save(TradeStore())
    }

    @Test
    fun testGetTradesIsGettingDataFromApi() {
        // Arrange
        whenever(api.getTrades(1)).thenReturn(getTrades(2))

        // Act
        val trades = getTradesRemoteRepository.getTrades(1)

        // Assert
        assertTrue(trades.trades.size == 2)
        assertTrue(trades.trades[0].trade_type == "SELL")

    }

    fun getTrades(times: Int): Trades {
        val tradeList = mutableListOf<Trade>()
        for (i in 1..times) {
            tradeList.add(getTrade())
        }
        return Trades(tradeList)
    }

    fun getTrade() = Trade(1, 1.0, 1.0,
            "2", "SELL")
}