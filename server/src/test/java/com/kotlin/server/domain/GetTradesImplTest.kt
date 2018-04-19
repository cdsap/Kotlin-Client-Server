package com.kotlin.server.domain

import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import com.kotlin.server.repository.GetTradesRepository
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetTradesImplTest {

    @Mock
    lateinit var localRepository: GetTradesRepository

    @Mock
    lateinit var remoteRepository: GetTradesRepository

    val getTradesImpl by lazy {
        GetTradesImpl(localRepository, remoteRepository)
    }

    @Test
    fun testGetTradesFromRepository() {
        // Arrange
        whenever(localRepository.getTrades(1)).thenReturn(getTrades(5))

        // Act
        val trades = getTradesImpl.getTrades(1)

        // Assert
        assertTrue(trades.trades.size == 5)
        assertTrue(trades.trades[0].trade_type == "SELL")
    }

    private fun getTrades(times: Int): Trades {
        val tradeList = mutableListOf<Trade>()
        for (i in 1..times) {
            tradeList.add(getTrade())
        }
        return Trades(tradeList)
    }

    private fun getTrade() = Trade(1, 1.0, 1.0,
            "2", "SELL")


}