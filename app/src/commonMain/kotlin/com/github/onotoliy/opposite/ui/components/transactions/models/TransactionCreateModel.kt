package com.github.onotoliy.opposite.viewmodel.transactions

import com.github.onotoliy.opposite.repositories.ITransactionRepository
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.viewmodel.AbstractCreateModel

class TransactionCreateModel(
    private val repository: ITransactionRepository
) : AbstractCreateModel<Transaction>() {

    override suspend fun create(value: Transaction): Transaction = repository.create(value)

}