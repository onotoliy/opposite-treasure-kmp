package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionListModel

@Composable
actual fun TransactionListView(viewModel: TransactionListModel, onSelect: (Screen) -> Unit) =
    TransactionListMobileView(viewModel, onSelect)