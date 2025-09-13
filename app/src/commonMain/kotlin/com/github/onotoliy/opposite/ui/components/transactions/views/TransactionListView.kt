package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowOutward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.data.TransactionType
import com.github.onotoliy.opposite.ui.components.AddFloatingActionButton
import com.github.onotoliy.opposite.ui.components.ListInfinity
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionListModel
import kotlin.time.ExperimentalTime

@Composable
expect fun TransactionListView(viewModel: TransactionListModel, onSelect: (Screen) -> Unit)

@Composable
fun TransactionListMobileView(viewModel: TransactionListModel, onSelect: (Screen) -> Unit) {
    LocalMobileScafoldState.current.floatingActionButton = {
        AddFloatingActionButton { onSelect(Screen.TransactionNewScreen) }
    }
    val state by viewModel.loadState.collectAsState()
    val values by viewModel.values.collectAsState()
    val hasLoadMore by viewModel.hasLoadMore.collectAsState()

    ListInfinity(
        loadingState = state,
        values = values,
        canLoadMore = hasLoadMore,
        onLoadMore = viewModel::load
    ) { event ->
        TransactionMobileItem(event, onSelect)
    }
}

@OptIn(ExperimentalTime::class)
@Composable
private fun TransactionMobileItem(transaction: Transaction, onSelect: (Screen) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .clickable { onSelect(Screen.TransactionViewScreen(transaction.uuid)) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val icon = when (transaction.type) {
            TransactionType.NONE -> Icons.AutoMirrored.Outlined.ArrowForward
            TransactionType.COST -> Icons.Outlined.ArrowDownward
            TransactionType.CONTRIBUTION -> Icons.Outlined.ArrowOutward
            TransactionType.WRITE_OFF -> Icons.AutoMirrored.Outlined.ArrowForward
            TransactionType.PAID -> Icons.AutoMirrored.Outlined.ArrowForward
            TransactionType.EARNED -> Icons.Outlined.ArrowOutward
        }
        val color = when (transaction.type) {
            TransactionType.NONE -> Color.Black
            TransactionType.COST -> Color.Red
            TransactionType.CONTRIBUTION -> Color.Green
            TransactionType.WRITE_OFF -> Color.Black
            TransactionType.PAID -> Color.Red
            TransactionType.EARNED -> Color.Green
        }
        Icon(imageVector = icon, contentDescription = null)

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = transaction.name)
                Text(color = color, text = transaction.cash)
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = transaction.person?.name ?: "")
                Text(text = transaction.transactionDate.toString())
            }

            HorizontalDivider()
        }
    }
}

@Composable
fun TransactionTableView(model: TransactionListModel, onSelect: (Screen) -> Unit) {
    /*DataTable(
        columns = {
            headerBackground {
                Box(modifier = Modifier.background(color = Color.LightGray))
            }
            column { Text("Тип") }
            column { Text("Название") }
            column { Text("Сумма") }
            column { Text("Пользователь") }
            column { Text("Событие") }
            column { Text("Дата создания") }
        }
    ) {
        transactions.forEach { record ->
            row(modifier = Modifier) {
                cell { Text(record.type.label) }
                cell {
                    Text(
                        modifier = Modifier.clickable {
                            onSelect(Screen.TransactionViewScreen(record.uuid))
                        },
                        text = record.name
                    )
                }
                cell { Text(record.cash) }
                cell {
                    Text(
                        modifier = Modifier.then(
                            record.person?.uuid?.let {
                                Modifier.clickable {
                                    onSelect(Screen.UserViewScreen(it))
                                }
                            } ?: Modifier
                        ),
                        text = record.person?.name ?: ""
                    )
                }
                cell {
                    Text(
                        modifier = Modifier.then(
                            record.event?.uuid?.let {
                                Modifier.clickable {
                                    onSelect(Screen.EventViewScreen(it))
                                }
                            } ?: Modifier
                        ),
                        text = record.event?.name ?: ""
                    )
                }
                cell { Text(record.transactionDate) }
            }
        }
    }*/
}
