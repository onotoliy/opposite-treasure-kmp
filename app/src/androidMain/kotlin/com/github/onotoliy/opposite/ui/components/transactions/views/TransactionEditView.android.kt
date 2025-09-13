package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionEditModel

@Composable
actual fun TransactionEditView(viewModel: TransactionEditModel, onSelect: (Screen) -> Unit) =
    TransactionEditMobileView(viewModel, onSelect)