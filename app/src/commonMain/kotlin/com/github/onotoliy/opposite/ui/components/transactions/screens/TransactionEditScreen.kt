package com.github.onotoliy.opposite.ui.transactions

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.components.events.views.EventEditView
import com.github.onotoliy.opposite.ui.components.transactions.TransactionEditView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionEditModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun TransactionEditScreen(
    uuid: String,
    model: TransactionEditModel = koinViewModel { parametersOf(uuid) },
    onSelect: (Screen) -> Unit
) {
    ApplicationScaffold(onSelect = onSelect) {
        TransactionEditView(model, onSelect)
    }
}

