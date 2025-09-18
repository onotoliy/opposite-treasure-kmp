package com.github.onotoliy.opposite.ui.transactions.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListAdapter
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListModel
import com.github.onotoliy.opposite.ui.transactions.views.TransactionListView
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun TransactionsTableScreen(model: TransactionListAdapter = koinInject(), nav: NavController) {
    ApplicationScaffold(onSelect = nav::goto) {
        TransactionListView(model, nav::goto)
    }
}
