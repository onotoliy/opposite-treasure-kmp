package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionNewViewModel

@Composable
actual fun TransactionCreateView(viewModel: TransactionNewViewModel, onSelect: (Screen) -> Unit) =
    TransactionCreateMobileView(viewModel, onSelect)