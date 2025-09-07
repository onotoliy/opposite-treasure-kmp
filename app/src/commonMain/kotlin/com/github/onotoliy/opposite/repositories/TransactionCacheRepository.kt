package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.data.Transaction
import kotlinx.coroutines.delay

class TransactionCacheRepository : ITransactionRepository {

    companion object {
        val TRANSACTIONS: MutableList<Transaction> = mutableListOf<Transaction>(
            newTransaction(1, 1, 2), newTransaction(2), newTransaction(3, 3, 4), newTransaction(4)
        )
    }

    override suspend fun get(uuid: String): Transaction {
        delay(1000)
        return TRANSACTIONS.firstOrNull() { it.uuid == uuid } ?: TRANSACTIONS.first()
    }


    override suspend fun getAll(q: String?, offset: Int, numberOfRows: Int): List<Transaction> {
        delay(1000)
        return TRANSACTIONS
    }

    override suspend fun create(user: Transaction): Transaction {
        delay(1000)
        TRANSACTIONS.add(user)

        return get(user.uuid)
    }

    override suspend fun update(user: Transaction): Transaction {
        delay(1000)
        delete(user.uuid)
        create(user)

        return get(user.uuid)
    }

    override suspend fun delete(uuid: String) {
        delay(1000)
        val list = TRANSACTIONS.filter { it.uuid != uuid }

        TRANSACTIONS.clear()
        TRANSACTIONS.addAll(list)
    }

}