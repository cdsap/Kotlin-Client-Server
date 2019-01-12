package com.kotlin.server.service

import com.kotlin.core.domain.entities.usecases.GetPairs
import javax.inject.Inject


class SyncPairsService @Inject constructor(private val getPairs: GetPairs) {

    fun sync() = getPairs.sync()

}