package com.kotlin.server.database

@Entity
class PairStore(
        @Id
        val id: Long,
        val primaryPairId: String,
        val secondaryPairId: String)