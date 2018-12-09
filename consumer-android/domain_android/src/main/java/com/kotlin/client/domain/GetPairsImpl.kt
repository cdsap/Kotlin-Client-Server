package com.kotlin.client.domain

import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.usecases.GetPairs

class GetPairsImpl(private val pairRepository: PairsRepository) : GetPairs {

    override fun get(): List<PairSymbol> = pairRepository.getPairs()
}