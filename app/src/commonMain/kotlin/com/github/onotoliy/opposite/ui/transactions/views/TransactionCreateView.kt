package com.github.onotoliy.opposite.ui.transactions.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.repositories.newTransaction
import com.github.onotoliy.opposite.treasure.model.Option
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.components.buttons.CancelButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.transactions.TransactionModificationLayout
import com.github.onotoliy.opposite.ui.transactions.models.TransactionCreateModel
import com.github.onotoliy.opposite.viewmodel.AbstractCreateView
import kotlinx.datetime.Clock
import kotlin.time.ExperimentalTime

@Composable
expect fun TransactionCreateView(viewModel: TransactionCreateModel)

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun TransactionCreateMobileView(viewModel: TransactionCreateModel) {
    val navHostController = LocalNavHostController.current

    LocalMobileScafoldState.current.titleTopBar = { Text("Создание мероприятия") }

    BaseTransactionCreateView(viewModel) { transaction ->
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
fun TransactionCreateWebView(viewModel: TransactionCreateModel) {
    val navHostController = LocalNavHostController.current

    BaseTransactionCreateView(viewModel) { transaction ->
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
private fun BaseTransactionCreateView(
    viewModel: TransactionCreateModel,
    actions: @Composable (transaction: Transaction) -> Unit
) {
    var type by remember { mutableStateOf(Transaction.Type.NONE) }
    var name by remember { mutableStateOf("") }
    var cash by remember { mutableStateOf("") }
    var person by remember { mutableStateOf<Option?>(null) }
    var event by remember { mutableStateOf<Option?>(null) }
    var transactionDate by remember { mutableStateOf(Clock.System.now()) }

    AbstractCreateView(viewModel) {
        TransactionModificationLayout(
            type = type,
            isTypeEnable = true,
            onTypeChanged = { type = it },
            cash = cash,
            isCashEnable = true,
            onCashChanged = { cash = it },
            user = person,
            isUserEnable = true,
            onUserChanged = { person = it },
            onUserQueryChanged = { listOf() },
            event = event,
            isEventEnable = true,
            onEventChanged = { event = it },
            onEventQueryChanged = { listOf() },
            transactionDate = transactionDate,
            onTransactionDateChange = { transactionDate = it },
            name = name,
            onNameChanged = { name = it }
        )

        actions(
            newTransaction(
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