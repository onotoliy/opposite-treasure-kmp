package com.github.onotoliy.opposite.ui.transactions.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
actual fun TransactionInformationView(
    transaction: Transaction,
    onSelect: (Screen) -> Unit
) = TransactionInformationWebView(transaction, onSelect)
