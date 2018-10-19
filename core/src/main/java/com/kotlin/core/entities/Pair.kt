package com.kotlin.core.entities

data class PairSymbol(
        val id: Long,
        val primarySymbol: Symbol,
        val secondarySymbol: Symbol,
        val rate: Double
)