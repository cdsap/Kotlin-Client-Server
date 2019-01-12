package com.kotlin.core.domain.entities.usecases.impl

import com.kotlin.core.domain.entities.PairSymbol
import com.kotlin.core.domain.entities.repository.PairsRepository
import com.kotlin.core.domain.entities.usecases.GetPairs

class GetPairsImpl(private val pairRepository: PairsRepository) : GetPairs {

    override fun get(): List<PairSymbol> {
        val list = pairRepository.getPairs()
        return if (list.isEmpty()) {
            sync()
        } else {
            pairRepository.getPairs()
        }
    }

    override fun sync(): List<PairSymbol> = pairRepository.syncPairs()
}