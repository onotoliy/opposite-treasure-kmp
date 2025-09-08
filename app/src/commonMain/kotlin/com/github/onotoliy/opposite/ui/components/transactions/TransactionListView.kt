package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
expect fun TransactionListView(transactions: List<Transaction>, onSelect: (Screen) -> Unit)