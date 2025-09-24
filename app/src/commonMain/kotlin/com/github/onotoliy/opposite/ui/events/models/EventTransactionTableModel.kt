package com.github.onotoliy.opposite.ui.events.models

import com.github.onotoliy.opposite.repositories.TransactionRepository
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListModel
import com.github.onotoliy.opposite.ui.transactions.models.TransactionTableModel
import com.github.onotoliy.opposite.viewmodel.Page

class EventTransactionTableModel(
    private val repository: TransactionRepository,
    private val eventID: String
) : TransactionTableModel(repository) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): Page<Transaction> {
        val page = repository.getAll(
            q = null,
            eventID = eventID,
            depositID = null,
            offset = offset,
            numberOfRows = numberOfRows
        )

        return Page(page.meta.total, page.context)
    }

}
