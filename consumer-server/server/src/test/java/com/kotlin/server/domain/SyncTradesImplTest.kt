package com.kotlin.server.domain

import com.kotlin.core.entities.Trade
import com.kotlin.core.entities.Trades
import com.nhaarman.mockito_kotlin.argForWhich
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SyncTradesImplTest {

    @Mock
    lateinit var localRepository: GetTradesRepository
    @Mock
    lateinit var remoteRepository: GetTradesRepository

    val syncTradesImplTest by lazy {
        SyncTradesImpl(localRepository, remoteRepository)
    }


    @Test
    fun testSyncTradesMapTradesFromRemoteRepository() {
        // Arrange
        whenever(remoteRepository.getTrades(1)).thenReturn(getTrades(1))
        // Act
        syncTradesImplTest.syncTrades(1)

        // Assert
        verify(localRepository).save(argForWhich {
            amount == 1.0 &&
                    rate == 1.0 &&
                    trade_type == "SELL" &&
                    trade_id == 1L
        })
    }

    @Test
    fun testSyncTradesCallsThreeTimesLocalRepository() {
        // Arrange
        whenever(remoteRepository.getTrades(1)).thenReturn(getTrades(5))
        // Act
        syncTradesImplTest.syncTrades(1)

        // Assert
        verify(localRepository, times(5)).save(argForWhich {
            amount == 1.0 &&
                    rate == 1.0 &&
                    trade_type == "SELL" &&
                    trade_id == 1L
        })

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
