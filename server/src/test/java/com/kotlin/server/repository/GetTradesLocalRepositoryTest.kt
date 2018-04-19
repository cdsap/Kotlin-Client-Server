package com.kotlin.server.repository

import com.kotlin.server.database.TradeStore
import com.googlecode.objectify.Objectify
import com.googlecode.objectify.cmd.LoadType
import com.googlecode.objectify.cmd.Loader
import com.googlecode.objectify.cmd.Saver
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.Assert.assertTrue
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
    @Mock
    lateinit var loader: Loader
    @Mock
    lateinit var loadType: LoadType<TradeStore>

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

    @Test
    fun testGetTradesAreReturningTradesStoresFromLocalRepository() {
        // TODO
        // Arrange
        whenever(objectify.load()).thenReturn(loader)
        whenever(loader.type(TradeStore::class.java)).thenReturn(loadType)
        loadType.count()

        // Act
        val trades = getTradesLocalRepository.getTrades(1)

        // Assert
        assertTrue(trades.trades.isEmpty())
        //   assertTrue(trades.trades[0].trade_type == "SELL")
        //   assertTrue(trades.trades[0].trade_date == "1")
        //   assertTrue(trades.trades[0].amount == 1.0)
        //  assertTrue(trades.trades[0].rate == 1.0)
    }

    fun getTradesStore() = listOf(getTrade())


    fun getTrade() = TradeStore(trade_id = 0L,
            trade_date = "1",
            trade_type = "SELL",
            amount = 1.0,
            rate = 1.0)
}