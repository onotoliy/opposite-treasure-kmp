package com.github.onotoliy.opposite.ui.users

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
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.components.events.views.EventListView
import com.github.onotoliy.opposite.ui.components.transactions.TransactionListView
import com.github.onotoliy.opposite.ui.components.users.UserInformationView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventsListModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionsListModel
import com.github.onotoliy.opposite.viewmodel.users.UserView
import com.github.onotoliy.opposite.viewmodel.users.UserViewModel
import com.github.onotoliy.opposite.viewmodel.users.UsersListModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun UserViewScreen(
    uuid: String,
    model: UserViewModel = koinViewModel { parametersOf(uuid) },
    elist: EventsListModel = koinViewModel(),
    tlist: TransactionsListModel = koinViewModel(),
    onSelect: (Screen) -> Unit
) {
    val state by model.state.collectAsState()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Информация", "Транзакции", "Долги")
    val icons = listOf(Icons.Filled.Info, Icons.Outlined.CurrencyExchange, Icons.Outlined.Event)

    UiStateScreen<UserView>(state, load = model::load) { data ->
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
                    0 -> UserInformationView(data.user, data.logo)
                    1 -> TransactionListView(tlist, onSelect = onSelect)
                    2 -> EventListView(elist, onSelect = onSelect)
                }
            }
        }
    }
}
