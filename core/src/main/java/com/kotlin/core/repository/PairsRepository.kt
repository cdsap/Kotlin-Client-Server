package com.kotlin.core.repository

import com.kotlin.core.entities.PairSymbol

interface PairsRepository {
    fun getPairs(): List<PairSymbol>
}