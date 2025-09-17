package com.github.onotoliy.opposite.ui.transactions.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListModel

@Composable
actual fun TransactionListView(
    viewModel: TransactionListModel,
    onSelect: (Screen) -> Unit
) = TransactionListMobileView(viewModel, onSelect)