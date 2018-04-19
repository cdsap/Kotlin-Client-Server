package com.kotlin.client.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface DbInterface {

    @Query("Select * FROM TradeDb order by trade_id DESC")
    fun getTradeDb(): List<TradeDb>

    @Insert
    fun insertTrade(pair: TradeDb): Long

}