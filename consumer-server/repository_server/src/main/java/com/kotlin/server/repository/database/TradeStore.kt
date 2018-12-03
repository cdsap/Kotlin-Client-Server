package com.kotlin.server.repository.database

import com.googlecode.objectify.ObjectifyService
import com.googlecode.objectify.Ref
import com.googlecode.objectify.annotation.Entity
import com.googlecode.objectify.annotation.Id
import com.googlecode.objectify.annotation.Index

@Entity
class TradeStore(
        @Id
        var trade_id: Long = 0L,
        var rate: Double = 0.0,
        var amount: Double = 0.0,
        var trade_date: String = "",
        var trade_type: String = "",
        @Index
        var pair: Ref<PairStore> = Ref.create(ObjectifyService.ofy().load().type(PairStore::class.java).id(1L).safe()))
