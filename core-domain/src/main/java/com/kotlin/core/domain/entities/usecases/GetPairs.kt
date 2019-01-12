package com.kotlin.core.domain.entities.usecases

import com.kotlin.core.domain.entities.PairSymbol

interface GetPairs {
    fun get(): List<PairSymbol>

    fun sync(): List<PairSymbol>
}