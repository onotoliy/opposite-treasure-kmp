package com.github.onotoliy.opposite.ui.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material3.Button
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
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.LabelledText
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.events.EventsTableScreen
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.transactions.TransactionsTableScreen
import com.github.onotoliy.opposite.viewmodel.events.EventView
import com.github.onotoliy.opposite.viewmodel.users.UserView
import com.github.onotoliy.opposite.viewmodel.users.UserViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun UserViewScreen(
    uuid: String,
    model: UserViewModel = koinViewModel { parametersOf(uuid) },
    onSelect: (Screen) -> Unit
) {
    val state by model.state.collectAsState()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Информация", "Транзакции", "Долги")
    val icons = listOf(Icons.Filled.Info, Icons.Outlined.CurrencyExchange, Icons.Outlined.Event)

    UiStateScreen<UserView>(state, load = model::load) { data ->
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
                0 -> InformationTab(data.user, data.logo)
                1 -> TransactionsScreen(data.transactions, onSelect = onSelect)
                2 -> DebtsScreen(data.debts, onSelect = onSelect)
            }
        }
    }
}

@Composable
private fun InformationTab(user: User, logo: DrawableResource) {
    Row(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(logo),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            modifier = Modifier.size(300.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LabelledText("Название", user.name)
            LabelledText("Депозит", user.deposit)
            LabelledText("Номер телефона", user.login)
            LabelledText("День рождения", user.birthday)

            Button(
                onClick = { /* обработка */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Сохранить")
            }
        }
    }
}

@Composable
private fun DebtsScreen(events: List<Event>, onSelect: (Screen) -> Unit) =
    EventsTableScreen(events, onSelect)

@Composable
private fun TransactionsScreen(transactions: List<Transaction>, onSelect: (Screen) -> Unit) =
    TransactionsTableScreen(transactions, onSelect)
