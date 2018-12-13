package com.kotlin.core.usecases.impl

import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.usecases.GetPairs

class GetPairsImpl(private val pairRepository: PairsRepository) : GetPairs {

    override fun get(): List<PairSymbol> {
        val pairs = pairRepository.getPairs()
        return if (pairs.isEmpty()) {
            return pairRepository.syncPairs()
        } else {
            pairs
        }
    }

    override fun sync(): List<PairSymbol> = pairRepository.syncPairs()

}