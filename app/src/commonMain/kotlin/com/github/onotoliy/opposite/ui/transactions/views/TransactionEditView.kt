package com.github.onotoliy.opposite.ui.transactions.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.components.buttons.CancelButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.transactions.TransactionModificationLayout
import com.github.onotoliy.opposite.ui.transactions.models.TransactionEditModel
import com.github.onotoliy.opposite.viewmodel.AbstractEditView
import kotlin.time.ExperimentalTime

@Composable
expect fun TransactionEditView(viewModel: TransactionEditModel)

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun TransactionEditMobileView(viewModel: TransactionEditModel) {
    val navHostController = LocalNavHostController.current

    LocalMobileScafoldState.current.titleTopBar = { Text("Изменение транзакции") }

    BaseTransactionEditView(viewModel) { transaction ->
        LocalMobileScafoldState.current.floatingActionButton = {
            SaveFloatingActionButton {
                viewModel.onSave(transaction) {
                    navHostController.goto(Screen.TransactionViewScreen(it.uuid))
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun TransactionEditWebView(viewModel: TransactionEditModel) {
    val navHostController = LocalNavHostController.current

    BaseTransactionEditView(viewModel) { transaction ->
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SaveButton {
                viewModel.onSave(transaction) {
                    navHostController.goto(Screen.TransactionViewScreen(transaction.uuid))
                }
            }
            CancelButton {
                navHostController.goto(Screen.TransactionListScreen)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
private fun BaseTransactionEditView(
    viewModel: TransactionEditModel,
    actions: @Composable (transaction: Transaction) -> Unit
) {
    val data by viewModel.info.collectAsState()

    var type by remember { mutableStateOf(data.type) }
    var name by remember { mutableStateOf(data.name) }
    var cash by remember { mutableStateOf(data.cash) }
    var person by remember { mutableStateOf(data.person) }
    var event by remember { mutableStateOf(data.event) }
    var transactionDate by remember { mutableStateOf(data.transactionDate) }

    LaunchedEffect(data) {
        type = data.type
        name = data.name
        cash = data.cash
        person = data.person
        event = data.event
        transactionDate = data.transactionDate
    }

    AbstractEditView(viewModel) {
        TransactionModificationLayout(
            type = type,
            isTypeEnable = false,
            onTypeChanged = { type = it },
            cash = cash,
            isCashEnable = false,
            onCashChanged = { cash = it },
            user = person,
            isUserEnable = false,
            onUserChanged = { person = it },
            onUserQueryChanged = { listOf() },
            event = event,
            isEventEnable = false,
            onEventChanged = { event = it },
            onEventQueryChanged = { listOf() },
            transactionDate = transactionDate,
            onTransactionDateChange = { transactionDate = it },
            name = name,
            onNameChanged = { name = it }
        )

        actions(
            data.copy(
                name = name,
                type = type,
                cash = cash,
                person = person,
                event = event,
                transactionDate = transactionDate
            )
        )
    }
}