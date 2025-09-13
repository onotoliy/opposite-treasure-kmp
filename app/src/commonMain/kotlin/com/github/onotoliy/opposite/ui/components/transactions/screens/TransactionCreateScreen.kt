package com.github.onotoliy.opposite.ui.components.transactions.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.components.transactions.TransactionCreateView
import com.github.onotoliy.opposite.ui.components.transactions.TransactionEditView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionEditModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionNewViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun TransactionCreateScreen(
    model: TransactionNewViewModel = koinViewModel(),
    onSelect: (Screen) -> Unit
) {
    ApplicationScaffold(onSelect = onSelect) {
        TransactionCreateView(model, onSelect)
    }
}
