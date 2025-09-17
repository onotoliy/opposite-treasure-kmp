package com.github.onotoliy.opposite.ui.users.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.components.UiStateScreen
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.events.views.EventListView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.transactions.views.TransactionListView
import com.github.onotoliy.opposite.ui.users.models.UserEventListModel
import com.github.onotoliy.opposite.ui.users.models.UserTransactionListModel
import com.github.onotoliy.opposite.ui.users.models.UserViewModel
import com.github.onotoliy.opposite.ui.users.views.UserInformationView
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun UserViewScreen(
    uuid: String,
    model: UserViewModel = koinViewModel { parametersOf(uuid) },
    transactions: UserTransactionListModel = koinViewModel { parametersOf(uuid) },
    users: UserEventListModel = koinViewModel { parametersOf(uuid) },
    onSelect: (Screen) -> Unit
) {
    val state by model.loadState.collectAsState()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Информация", "Транзакции", "Долги")
    val icons = listOf(Icons.Filled.Info, Icons.Outlined.CurrencyExchange, Icons.Outlined.Event)

    UiStateScreen(state, load = model::load) {
        ApplicationScaffold(
            onSelect = onSelect
        ) {
            Column {
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
                    0 -> UserInformationView(model.info.value, model.logo.value , onSelect)
                    1 -> TransactionListView(transactions, onSelect = onSelect)
                    2 -> EventListView(users, onSelect = onSelect)
                }
            }
        }
    }
}
