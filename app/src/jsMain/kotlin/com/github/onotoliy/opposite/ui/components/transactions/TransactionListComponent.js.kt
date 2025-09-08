package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.ui.components.transactions.list.TransactionTableView
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
actual fun TransactionListView(transactions: List<Transaction>, onSelect: (Screen) -> Unit) =
    TransactionTableView(transactions, onSelect)