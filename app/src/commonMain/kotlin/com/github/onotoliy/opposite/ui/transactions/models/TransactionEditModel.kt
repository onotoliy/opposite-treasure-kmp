package com.github.onotoliy.opposite.ui.transactions.models

import com.github.onotoliy.opposite.repositories.TransactionRepository
import com.github.onotoliy.opposite.repositories.newTransaction
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.viewmodel.AbstractEditModel

class TransactionEditModel(
    private val repository: TransactionRepository,
    private val uuid: String
) : AbstractEditModel<Transaction>(uuid) {

    override suspend fun get(uuid: String): Transaction = repository.get(uuid)

    override suspend fun update(value: Transaction): Transaction = repository.update(value)

    override val defaultValue: Transaction
        get() = newTransaction()

}
