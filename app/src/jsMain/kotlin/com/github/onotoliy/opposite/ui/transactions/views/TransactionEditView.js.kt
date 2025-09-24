package com.github.onotoliy.opposite.ui.transactions.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.transactions.models.TransactionEditModel

@Composable
actual fun TransactionEditView(
    viewModel: TransactionEditModel
) = TransactionEditWebView(viewModel)
