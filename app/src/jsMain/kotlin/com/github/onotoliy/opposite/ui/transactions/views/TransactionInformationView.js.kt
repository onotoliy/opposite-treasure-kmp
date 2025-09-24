package com.github.onotoliy.opposite.ui.transactions.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.transactions.models.TransactionViewModel

@Composable
actual fun TransactionInformationView(
    viewModel: TransactionViewModel
) = TransactionInformationWebView(viewModel)
