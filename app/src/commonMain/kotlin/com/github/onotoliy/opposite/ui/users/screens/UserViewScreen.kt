package com.github.onotoliy.opposite.ui.users.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Event
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
import androidx.navigation.NavController
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.events.models.EventTransactionListAdapter
import com.github.onotoliy.opposite.ui.events.views.EventListView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.transactions.views.TransactionListView
import com.github.onotoliy.opposite.ui.users.models.UserEventListAdapter
import com.github.onotoliy.opposite.ui.users.models.UserEventListModel
import com.github.onotoliy.opposite.ui.users.models.UserTransactionListAdapter
import com.github.onotoliy.opposite.ui.users.models.UserTransactionListModel
import com.github.onotoliy.opposite.ui.users.models.UserViewModel
import com.github.onotoliy.opposite.ui.users.views.UserInformationView
import com.github.onotoliy.opposite.viewmodel.UiState
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserViewScreen(
    uuid: String,
    nav: NavController,
    viewModel: UserViewModel = koinViewModel { parametersOf(uuid) },
    transactions: UserTransactionListAdapter = koinInject { parametersOf(uuid) },
    users: UserEventListAdapter = koinInject { parametersOf(uuid) },
) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Информация", "Транзакции", "Долги")
    val icons = listOf(Icons.Filled.Info, Icons.Outlined.CurrencyExchange, Icons.Outlined.Event)

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    ApplicationScaffold(
        onSelect = nav::goto
    ) {
        Column {
            when (state) {
                is UiState.Error -> {}
                UiState.Loading -> LinearProgressIndicator(Modifier.fillMaxWidth())
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
                0 -> UserInformationView(viewModel)
                1 -> TransactionListView(transactions, nav::goto)
                2 -> EventListView(users, nav::goto)
            }
        }
    }
}
