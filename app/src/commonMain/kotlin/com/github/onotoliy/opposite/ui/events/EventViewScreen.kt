package com.github.onotoliy.opposite.ui.events

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
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.transactions.TransactionsTableScreen
import com.github.onotoliy.opposite.ui.users.UsersTableScreen
import com.github.onotoliy.opposite.viewmodel.events.EventView
import com.github.onotoliy.opposite.viewmodel.events.EventViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EventViewScreen(
    uuid: String,
    model: EventViewModel = koinViewModel { parametersOf(uuid) },
    onSelect: (Screen) -> Unit
) {
    val state by model.state.collectAsState()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Информация", "Транзакции", "Должники")
    val icons = listOf(Icons.Filled.Info, Icons.Outlined.CurrencyExchange, Icons.Outlined.People)

    UiStateScreen<EventView>(state, load = model::load) { data ->
        Column {
            TabRow(selectedTabIndex = selectedTabIndex) {
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
                0 -> InformationTab(data.event, data.logo)
                1 -> TransactionsScreen(data.transactions, onSelect = onSelect)
                2 -> DebtorsScreen(data.debtors, onSelect = onSelect)
            }
        }
    }
}

@Composable
private fun InformationTab(event: Event, logo: DrawableResource) {
    var name by remember { mutableStateOf(event.name) }
    var author by remember { mutableStateOf(event.author.name) }
    var deadline by remember { mutableStateOf(event.deadline) }
    var contribution by remember { mutableStateOf(event.contribution) }
    var creationDate by remember { mutableStateOf(event.creationDate) }

    Row(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(logo),
            contentDescription = "Пример",
            alignment = Alignment.TopCenter,
            modifier = Modifier.size(300.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LabelledText("Название", name)
            LabelledText("Сумма", contribution)
            LabelledText("Сдать до", deadline)
            LabelledText("Автор", author)
            LabelledText("Дата создания", creationDate)

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
private fun DebtorsScreen(users: List<User>, onSelect: (Screen) -> Unit) =
    UsersTableScreen(users, onSelect)

@Composable
private fun TransactionsScreen(transactions: List<Transaction>, onSelect: (Screen) -> Unit) =
    TransactionsTableScreen(transactions, onSelect)
