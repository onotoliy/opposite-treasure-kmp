package com.github.onotoliy.opposite.ui.transactions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.components.TransactionTable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionsListModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun TransactionsTableScreen(model: TransactionsListModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    val state by model.state.collectAsState()

    UiStateScreen<List<Transaction>>(state, load = model::load) { events ->
        TransactionsTableScreen(events, onSelect)
    }
}

@Composable
fun TransactionsTableScreen(transactions: List<Transaction>, onSelect: (Screen) -> Unit) {
    TransactionTable(transactions, onSelect)
}
