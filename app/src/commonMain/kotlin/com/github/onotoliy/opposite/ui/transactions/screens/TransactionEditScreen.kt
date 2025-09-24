package com.github.onotoliy.opposite.ui.transactions.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.transactions.views.TransactionEditView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.transactions.models.TransactionEditModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun TransactionEditScreen(
    uuid: String,
    model: TransactionEditModel = koinViewModel { parametersOf(uuid) }
) {
    ApplicationScaffold {
        TransactionEditView(model)
    }
}

