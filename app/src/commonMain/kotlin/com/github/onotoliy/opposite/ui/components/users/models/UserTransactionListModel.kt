package com.github.onotoliy.opposite.ui.components.users.models

import com.github.onotoliy.opposite.repositories.TransactionRepository
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionListModel

class UserTransactionListModel(
    private val repository: TransactionRepository,
    private val userID: String
) : TransactionListModel(repository) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Transaction> =
        repository.getAll(null, eventID = null, depositID = userID,  offset, numberOfRows)

}