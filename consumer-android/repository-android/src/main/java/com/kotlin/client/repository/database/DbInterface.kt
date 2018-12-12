package com.kotlin.client.database

import androidx.room.*

@Dao
interface DbInterface {

    @Query("Select * FROM TradeDb where pair = :id order by trade_id DESC")
    fun getTradeDb(id: Long): List<TradeDb>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTrade(pair: TradeDb): Long

    @Update
    fun updatePair(pair: PairDb)

    @Insert
    fun insertPair(pair: PairDb): Long

    @Insert
    fun insertSymbol(pair: SymbolDb): Long

    @Query("Select * FROM TradeDb where pair = :id order by trade_id DESC LIMIT 1")
    fun getLastTradesById(id: Long): TradeDb

    @Query("Select * FROM PairDb")
    fun getPairs(): List<PairDb>

    @Query("Select * FROM PairDb where id=:id")
    fun getPairs(id: Long): PairDb

}