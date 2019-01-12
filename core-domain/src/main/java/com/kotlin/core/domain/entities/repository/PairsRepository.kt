package com.kotlin.core.domain.entities.repository

import com.kotlin.core.domain.entities.PairSymbol

interface PairsRepository {
    fun getPairs(): List<PairSymbol>

    fun syncPairs(): List<PairSymbol>
}
