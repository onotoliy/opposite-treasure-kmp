package com.github.onotoliy.opposite.ui.events.models

import com.github.onotoliy.opposite.repositories.TransactionRepository
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListAdapter
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListModel

class EventTransactionListAdapter(
    private val eventID: String,
    override val table: EventTransactionTableModel,
    override val list: EventTransactionListModel
) : TransactionListAdapter(table, list)