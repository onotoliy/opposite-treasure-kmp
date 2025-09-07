package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.data.Transaction

interface ITransactionRepository {

    suspend fun get(uuid: String): Transaction

    suspend fun getAll(q: String? = null, offset: Int = 0, numberOfRows: Int = 10): List<Transaction>

    suspend fun create(user: Transaction): Transaction

    suspend fun update(user: Transaction): Transaction

    suspend fun delete(uuid: String)

}