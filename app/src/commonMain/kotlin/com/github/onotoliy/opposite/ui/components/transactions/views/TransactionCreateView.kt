package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.Option
import com.github.onotoliy.opposite.data.TransactionType
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.SaveButton
import com.github.onotoliy.opposite.ui.components.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionNewViewModel
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Composable
expect fun TransactionCreateView(viewModel: TransactionNewViewModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class)
fun TransactionCreateMobileView(viewModel: TransactionNewViewModel, onSelect: (Screen) -> Unit) {
    var type by remember { mutableStateOf(TransactionType.NONE) }
    var name by remember { mutableStateOf("") }
    var cash by remember { mutableStateOf("") }
    var person by remember { mutableStateOf<Option?>(null) }
    var event by remember { mutableStateOf<Option?>(null) }
    var transactionDate by remember { mutableStateOf(Clock.System.now()) }

    LocalMobileScafoldState.current.topBar = { Text("Создание мероприятия") }
    LocalMobileScafoldState.current.floatingActionButton = {
        SaveFloatingActionButton {
            viewModel.onSave(
                    name = name,
                    type = type,
                    cash = cash,
                    person = person,
                    event = event,
                    transactionDate = transactionDate
            ) {
                onSelect(Screen.TransactionViewScreen(it.uuid))
            }
        }
    }

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
}

@Composable
@OptIn(ExperimentalTime::class)
fun TransactionCreateWebView(viewModel: TransactionNewViewModel, onSelect: (Screen) -> Unit) {
    var type by remember { mutableStateOf(TransactionType.NONE) }
    var name by remember { mutableStateOf("") }
    var cash by remember { mutableStateOf("") }
    var person by remember { mutableStateOf<Option?>(null) }
    var event by remember { mutableStateOf<Option?>(null) }
    var transactionDate by remember { mutableStateOf(Clock.System.now()) }

    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
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

        Row {
            SaveButton {
                viewModel.onSave(
                    name = name,
                    type = type,
                    cash = cash,
                    person = person,
                    event = event,
                    transactionDate = transactionDate
                ) {
                    onSelect(Screen.TransactionViewScreen(it.uuid))
                }
            }
            SaveButton {
                onSelect(Screen.EventsScreen)
            }
        }
    }
}