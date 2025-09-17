package com.github.onotoliy.opposite.ui.transactions.screens

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.components.transactions.TransactionCreateView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionCreateModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun TransactionCreateScreen(
    model: TransactionCreateModel = koinViewModel(),
    onSelect: (Screen) -> Unit
) {
    ApplicationScaffold(onSelect = onSelect) {
        TransactionCreateView(model, onSelect)
    }
}
