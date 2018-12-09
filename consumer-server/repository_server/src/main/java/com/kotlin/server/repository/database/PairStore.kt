package com.kotlin.server.repository.database

import com.googlecode.objectify.Ref
import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id


@Entity
class PairStore(
        @Id var id: Long = 0L,
        var primaryPairId: Ref<SymbolStore> = Ref.create(SymbolStore("BTH")),
        var secondaryPairId: Ref<SymbolStore> = Ref.create(SymbolStore("BTH")),
        var rate: Double = 0.0,
        var volume: Double = 0.0
)
