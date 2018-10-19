package com.kotlin.core.usecases

import com.kotlin.core.entities.PairSymbol

interface GetPairs {
    fun get(): List<PairSymbol>
}