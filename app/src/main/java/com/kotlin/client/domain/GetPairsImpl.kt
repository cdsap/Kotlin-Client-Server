package com.kotlin.client.domain

import com.kotlin.client.repository.PairRepository
import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.usecases.GetPairs

class GetPairsImpl(val pairRepository: PairRepository) : GetPairs {

    override fun get(): List<PairSymbol> = pairRepository.getPairs()

}