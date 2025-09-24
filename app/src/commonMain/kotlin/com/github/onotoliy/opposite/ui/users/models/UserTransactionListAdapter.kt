package com.github.onotoliy.opposite.ui.users.models

import com.github.onotoliy.opposite.repositories.TransactionRepository
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListAdapter
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListModel

class UserTransactionListAdapter(
    private val userID: String,
    override val list: UserTransactionListModel,
    override val table: UserTransactionTableModel
) : TransactionListAdapter(table, list)
