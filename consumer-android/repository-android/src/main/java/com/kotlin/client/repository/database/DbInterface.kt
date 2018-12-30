package com.kotlin.client.repository.database

import androidx.room.*

@Dao
interface  DbInterface {

    @Query("Select * FROM TradeDb where pair = :id order by trade_id DESC")
    fun getTradeDb(id: Long): List<TradeDb>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTrade(pair: TradeDb): Long

    @Update
    fun updatePair(pair: PairDb)

    @Insert
    fun insertPair(pair: PairDb): Long

    @Query("Select * FROM PairDb")
    fun getPairs(): List<PairDb>

    @Query("Select * FROM PairDb where id=:id")
    fun getPairs(id: Long): PairDb

}