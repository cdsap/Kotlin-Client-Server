package com.kotlin.client.repository

import com.kotlin.core.entities.PairSymbol

interface PairRepository {
    fun getPairs(): List<PairSymbol>
}