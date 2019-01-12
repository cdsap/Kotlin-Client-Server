package com.kotlin.core.domain.entities

data class PairSymbol(
        val id: Long,
        val primarySymbol: String,
        val secondarySymbol: String,
        val rate: Double,
        val volume: Double
)