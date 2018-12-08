package com.kotlin.core.repository


class TestRepos<out T : LocalSource<E>,
        out R : RemoteSource<Q>,
        Q,
        E>(private val db: T,
           private val api: R,
           private val mapper: Mapper<Q, E>) : SyncRepository {
    override fun sync() {
        api.sync().forEach {
            db.save(mapper.map(it))
        }
    }

}

interface Mapper<E, T> {
    fun map(e: E): T
}

