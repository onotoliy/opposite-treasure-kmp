package com.github.onotoliy.opposite.ui.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.components.TransactionTableComponent
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.UsersTableScreen
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionsListModel
import com.github.onotoliy.opposite.viewmodel.users.UsersListModel
import io.github.windedge.table.DataTable
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun TransactionsTableScreen(model: TransactionsListModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    val state by model.state.collectAsState()

    UiStateScreen<List<Transaction>>(state, load = model::load) { events ->
        TransactionsTableScreen(events, onSelect)
    }
}

@Composable
fun TransactionsTableScreen(transactions: List<Transaction>, onSelect: (Screen) -> Unit) {
    TransactionTableComponent(transactions, onSelect)
}
