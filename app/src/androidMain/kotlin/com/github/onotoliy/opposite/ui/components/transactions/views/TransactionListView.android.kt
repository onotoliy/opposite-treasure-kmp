package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionsListModel

@Composable
actual fun TransactionListView(viewModel: TransactionsListModel, onSelect: (Screen) -> Unit) =
    TransactionListMobileView(viewModel, onSelect)