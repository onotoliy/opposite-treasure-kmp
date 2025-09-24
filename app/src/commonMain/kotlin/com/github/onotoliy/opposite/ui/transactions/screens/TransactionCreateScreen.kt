package com.github.onotoliy.opposite.ui.transactions.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.transactions.views.TransactionCreateView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.transactions.models.TransactionCreateModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun TransactionCreateScreen(
    model: TransactionCreateModel = koinViewModel()
) {
    ApplicationScaffold {
        TransactionCreateView(model)
    }
}
