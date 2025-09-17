package com.github.onotoliy.opposite.ui.events.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.transactions.views.TransactionListView
import com.github.onotoliy.opposite.ui.events.models.EventTransactionListModel
import com.github.onotoliy.opposite.ui.events.models.EventUserListModel
import com.github.onotoliy.opposite.ui.events.models.EventViewModel
import com.github.onotoliy.opposite.ui.events.views.EventInformationView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.views.UserListView
import com.github.onotoliy.opposite.viewmodel.UiState
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventViewScreen(
    uuid: String,
    viewModel: EventViewModel = koinViewModel { parametersOf(uuid) },
    transactions: EventTransactionListModel = koinViewModel { parametersOf(uuid) },
    users: EventUserListModel = koinViewModel { parametersOf(uuid) },
    onSelect: (Screen) -> Unit
) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Информация", "Транзакции", "Должники")
    val icons = listOf(Icons.Filled.Info, Icons.Outlined.CurrencyExchange, Icons.Outlined.People)

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    ApplicationScaffold(
        onSelect = onSelect
    ) {
        Column {
            when (state) {
                is UiState.Error -> {}
                UiState.Loading -> LinearProgressIndicator()
                is UiState.Success -> {}
            }

            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 0.dp
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(imageVector = icons[index], contentDescription = title)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = title)
                        }
                    }
                }
            }

            when (selectedTabIndex) {
                0 -> EventInformationView(viewModel.info.value, viewModel.logo.value, onSelect)
                1 -> TransactionListView(transactions, onSelect = onSelect)
                2 -> UserListView(users, onSelect = onSelect)
            }
        }
    }
}