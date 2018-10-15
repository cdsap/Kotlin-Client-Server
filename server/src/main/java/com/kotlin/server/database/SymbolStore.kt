package com.kotlin.server.database

import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id

@Entity
class SymbolStore(
        @Id
        var symbol: String = "BTH")