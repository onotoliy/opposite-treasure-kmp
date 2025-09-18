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
import com.github.onotoliy.opposite.ui.components.buttons.CancelButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.transactions.TransactionModificationLayout
import com.github.onotoliy.opposite.ui.transactions.models.TransactionEditModel
import com.github.onotoliy.opposite.viewmodel.UiState
import kotlin.time.ExperimentalTime

@Composable
expect fun TransactionEditView(viewModel: TransactionEditModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun TransactionEditMobileView(viewModel: TransactionEditModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val data by viewModel.info.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    var type by remember { mutableStateOf(data.type) }
    var name by remember { mutableStateOf(data.name) }
    var cash by remember { mutableStateOf(data.cash) }
    var person by remember { mutableStateOf(data.person) }
    var event by remember { mutableStateOf(data.event) }
    var transactionDate by remember { mutableStateOf(data.transactionDate) }

    LocalMobileScafoldState.current.topBar = { Text("Изменение транзакции") }
    LocalMobileScafoldState.current.floatingActionButton = {
        SaveFloatingActionButton {
            viewModel.onSave(
                data.copy(
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

    Column {
        when (state) {
            is UiState.Error -> {}
            UiState.Loading -> LinearProgressIndicator()
            is UiState.Success -> {}
        }

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
    }
}

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun TransactionEditWebView(viewModel: TransactionEditModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val data by viewModel.info.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    var type by remember { mutableStateOf(data.type) }
    var name by remember { mutableStateOf(data.name) }
    var cash by remember { mutableStateOf(data.cash) }
    var person by remember { mutableStateOf(data.person) }
    var event by remember { mutableStateOf(data.event) }
    var transactionDate by remember { mutableStateOf(data.transactionDate) }

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

        Row {
            SaveButton {
                viewModel.onSave(
                    data.copy(
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
            CancelButton {
                onSelect(Screen.TransactionsScreen)
            }
        }
    }
}
