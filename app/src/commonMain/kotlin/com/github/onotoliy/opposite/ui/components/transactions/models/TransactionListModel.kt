package com.github.onotoliy.opposite.viewmodel.transactions

import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.repositories.ITransactionRepository
import com.github.onotoliy.opposite.viewmodel.AbstractInfinityListModel

open class TransactionListModel(
    private val repository: ITransactionRepository
) : AbstractInfinityListModel<Transaction>() {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Transaction> =
        repository.getAll(null, null, offset, numberOfRows)

}