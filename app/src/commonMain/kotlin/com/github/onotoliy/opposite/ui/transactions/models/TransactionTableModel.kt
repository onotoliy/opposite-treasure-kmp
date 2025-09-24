package com.github.onotoliy.opposite.ui.transactions.models

import com.github.onotoliy.opposite.repositories.TransactionRepository
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.viewmodel.AbstractInfinityTableModel
import com.github.onotoliy.opposite.viewmodel.Page

open class TransactionTableModel(
    private val repository: TransactionRepository
) : AbstractInfinityTableModel<Transaction>() {

    override suspend fun getAll(offset: Int, numberOfRows: Int): Page<Transaction> {
        val page = repository.getAll(
            q = null,
            eventID = null,
            depositID = null,
            offset = offset,
            numberOfRows = numberOfRows
        )

        return Page(page.meta.total, page.context)
    }

}
