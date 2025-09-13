package com.github.onotoliy.opposite.ui.components.transactions.screens

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.components.transactions.TransactionListView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionListModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun TransactionsTableScreen(model: TransactionListModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    ApplicationScaffold(onSelect = onSelect) {
        TransactionListView(model, onSelect)
    }
}
