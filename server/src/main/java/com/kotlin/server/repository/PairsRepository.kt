package com.kotlin.server.repository

import com.kotlin.core.entities.PairSymbol
import com.kotlin.server.database.PairStore

interface PairsRepository {
    fun getPairs(): List<PairSymbol>
}