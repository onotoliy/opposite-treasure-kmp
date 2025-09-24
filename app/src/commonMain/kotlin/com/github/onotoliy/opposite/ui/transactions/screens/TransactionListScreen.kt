package com.github.onotoliy.opposite.ui.transactions.screens

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListAdapter
import com.github.onotoliy.opposite.ui.transactions.views.TransactionListView
import org.koin.compose.koinInject


@Composable
fun TransactionListScreen(model: TransactionListAdapter = koinInject()) {
    ApplicationScaffold {
        TransactionListView(model, true)
    }
}
