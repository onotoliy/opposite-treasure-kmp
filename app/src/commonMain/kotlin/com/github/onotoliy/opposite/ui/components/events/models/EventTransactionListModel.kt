package com.github.onotoliy.opposite.ui.components.events.models

import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.repositories.ITransactionRepository
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionListModel

class EventTransactionListModel(
    private val repository: ITransactionRepository,
    private val eventID: String
) : TransactionListModel(repository) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Transaction> =
        repository.getAll(null, eventID = eventID, offset, numberOfRows)

}