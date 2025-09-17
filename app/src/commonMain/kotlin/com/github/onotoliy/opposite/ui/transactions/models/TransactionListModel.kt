package com.github.onotoliy.opposite.ui.transactions.models

import com.github.onotoliy.opposite.repositories.TransactionRepository
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.viewmodel.AbstractInfinityListModel

open class TransactionListModel(
    private val repository: TransactionRepository
) : AbstractInfinityListModel<Transaction>() {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Transaction> =
        repository.getAll(
            null,
            null,
            depositID = null,
            offset = offset,
            numberOfRows = numberOfRows
        )

}