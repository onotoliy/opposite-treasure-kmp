package com.github.onotoliy.opposite.ui.components.users.models

import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.repositories.ITransactionRepository
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionListModel

class UserTransactionListModel(
    private val repository: ITransactionRepository,
    private val userID: String
) : TransactionListModel(repository) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Transaction> =
        repository.getAll(null, eventID = userID, offset, numberOfRows)

}