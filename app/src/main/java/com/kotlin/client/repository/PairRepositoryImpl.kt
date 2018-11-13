package com.kotlin.client.repository

import com.kotlin.client.database.DbInterface
import com.kotlin.core.entities.PairSymbol

class PairRepositoryImpl(val db: DbInterface) : PairRepository {
    override fun getPairs(): List<PairSymbol> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}