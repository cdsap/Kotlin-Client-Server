package com.kotlin.core.entities

data class PairAndTrades(
        val pairSymbol: PairSymbol,
        val listOfTrades: Trades
)