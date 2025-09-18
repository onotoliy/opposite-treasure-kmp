package com.github.onotoliy.opposite.ui.transactions.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.repositories.newTransaction
import com.github.onotoliy.opposite.treasure.model.Option
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.components.buttons.SaveButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.transactions.TransactionModificationLayout
import com.github.onotoliy.opposite.ui.transactions.models.TransactionCreateModel
import com.github.onotoliy.opposite.viewmodel.UiState
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Composable
expect fun TransactionCreateView(viewModel: TransactionCreateModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun TransactionCreateMobileView(viewModel: TransactionCreateModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    var type by remember { mutableStateOf(Transaction.Type.NONE) }
    var name by remember { mutableStateOf("") }
    var cash by remember { mutableStateOf("") }
    var person by remember { mutableStateOf<Option?>(null) }
    var event by remember { mutableStateOf<Option?>(null) }
    var transactionDate by remember { mutableStateOf(Clock.System.now()) }

    LocalMobileScafoldState.current.topBar = { Text("Создание мероприятия") }
    LocalMobileScafoldState.current.floatingActionButton = {
        SaveFloatingActionButton {
            viewModel.onSave(
                newTransaction(
                    name = name,
                    type = type,
                    cash = cash,
                    person = person,
                    event = event,
                    transactionDate = transactionDate
                )
            ) {
                onSelect(Screen.TransactionViewScreen(it.uuid))
            }
        }
    }

    val events by viewModel.events.collectAsState()
    val deposits by viewModel.deposits.collectAsState()

    Column {
        when (state) {
            is UiState.Error -> {}
            UiState.Loading -> LinearProgressIndicator()
            is UiState.Success -> {}
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
            onUserQueryChanged = {
                viewModel.getDeposits(it)
                deposits
            },
            event = event,
            isEventEnable = true,
            onEventChanged = { event = it },
            onEventQueryChanged = {
                viewModel.getEvents(it)
                events
            },
            transactionDate = transactionDate,
            onTransactionDateChange = { transactionDate = it },
            name = name,
            onNameChanged = { name = it }
        )
    }
}

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun TransactionCreateWebView(viewModel: TransactionCreateModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    var type by remember { mutableStateOf(Transaction.Type.NONE) }
    var name by remember { mutableStateOf("") }
    var cash by remember { mutableStateOf("") }
    var person by remember { mutableStateOf<Option?>(null) }
    var event by remember { mutableStateOf<Option?>(null) }
    var transactionDate by remember { mutableStateOf(Clock.System.now()) }

    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when (state) {
            is UiState.Error -> {}
            UiState.Loading -> LinearProgressIndicator()
            is UiState.Success -> {}
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

        Row {
            SaveButton {
                viewModel.onSave(
                    newTransaction(
                        name = name,
                        type = type,
                        cash = cash,
                        person = person,
                        event = event,
                        transactionDate = transactionDate
                    )
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
