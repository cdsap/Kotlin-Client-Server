package com.kotlin.core.entities

data class PairSymbol(
        val id: Long,
        val primarySymbol: String,
        val secondarySymbol: String,
        val rate: Double
)