package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.treasure.api.TransactionResourceApi
import com.github.onotoliy.opposite.treasure.model.Transaction

class ITransactionRepository(
    private val api: TransactionResourceApi
) {

    suspend fun get(uuid: String): Transaction {
        return api.getTransactionResource(uuid).body()
    }

    suspend fun getAll(
        q: String?, eventID: String?, depositID: String?, offset: Int, numberOfRows: Int
    ): List<Transaction> {
        return api
            .getAllTransactionResource(
                name = q,
                event = eventID,
                user = depositID,
                offset = offset,
                numberOfRows = numberOfRows
            )
            .body().context ?: listOf()
    }

    suspend fun create(transaction: Transaction): Transaction {
        return api.createTransactionResource(transaction).body()
    }

    suspend fun update(transaction: Transaction): Transaction {
        return api.updateTransactionResource(transaction).body()
    }

    suspend fun delete(uuid: String) {
        api.deleteTransactionResource(uuid)
    }

}