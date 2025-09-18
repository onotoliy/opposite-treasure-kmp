package com.github.onotoliy.opposite.ui.events.models

import com.github.onotoliy.opposite.repositories.TransactionRepository
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListModel

class EventTransactionListModel(
    private val repository: TransactionRepository,
    private val eventID: String
) : TransactionListModel(repository) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Transaction> =
        repository.getAll(
            q = null,
            eventID = eventID,
            depositID = null,
            offset = offset,
            numberOfRows = numberOfRows
        )

}
