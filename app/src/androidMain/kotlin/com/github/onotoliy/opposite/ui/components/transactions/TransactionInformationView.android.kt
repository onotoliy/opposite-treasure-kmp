package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.ui.components.transactions.view.TransactionInformationMobileView
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
actual fun TransactionInformationView(transaction: Transaction, onSelect: (Screen) -> Unit) =
    TransactionInformationMobileView(transaction, onSelect)