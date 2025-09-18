package com.github.onotoliy.opposite.ui.transactions.views

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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.repositories.NUMBER_OF_ROWS
import com.github.onotoliy.opposite.repositories.label
import com.github.onotoliy.opposite.repositories.toMoneyPrettyString
import com.github.onotoliy.opposite.repositories.toPrettyString
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.components.buttons.AddFloatingActionButton
import com.github.onotoliy.opposite.ui.components.infinity.ListInfinity
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListAdapter
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListModel
import com.github.onotoliy.opposite.ui.transactions.models.TransactionTableModel
import com.github.onotoliy.opposite.viewmodel.UiState
import io.github.windedge.table.m3.PaginatedDataTable
import io.github.windedge.table.rememberPaginationState
import kotlin.time.ExperimentalTime

@Composable
expect fun TransactionListView(listAdapter: TransactionListAdapter, onSelect: (Screen) -> Unit)

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
            Transaction.Type.NONE -> Icons.AutoMirrored.Outlined.ArrowForward
            Transaction.Type.COST -> Icons.Outlined.ArrowDownward
            Transaction.Type.CONTRIBUTION -> Icons.Outlined.ArrowOutward
            Transaction.Type.WRITE_OFF -> Icons.AutoMirrored.Outlined.ArrowForward
            Transaction.Type.PAID -> Icons.AutoMirrored.Outlined.ArrowForward
            Transaction.Type.EARNED -> Icons.Outlined.ArrowOutward
        }
        val color = when (transaction.type) {
            Transaction.Type.NONE -> Color.Black
            Transaction.Type.COST -> Color.Red
            Transaction.Type.CONTRIBUTION -> Color.Green
            Transaction.Type.WRITE_OFF -> Color.Black
            Transaction.Type.PAID -> Color.Red
            Transaction.Type.EARNED -> Color.Green
        }
        Icon(imageVector = icon, contentDescription = null)

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = transaction.name)
                Text(color = color, text = transaction.cash.toMoneyPrettyString())
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = transaction.person?.name ?: "")
                Text(text = transaction.transactionDate.toPrettyString())
            }

            HorizontalDivider()
        }
    }
}

@Composable
fun TransactionTableView(viewModel: TransactionTableModel, onSelect: (Screen) -> Unit) {
    val loadingState by viewModel.loadState.collectAsState()
    val values by viewModel.values.collectAsState()
    val total by viewModel.total.collectAsState()

    val paginationState = rememberPaginationState(total, pageSize = NUMBER_OF_ROWS)

    Column {
        when (loadingState) {
            is UiState.Error -> {
                Text("Ошибка: ${(loadingState as UiState.Error).message}")
            }

            is UiState.Loading -> {
                LinearProgressIndicator()
            }

            is UiState.Success -> {

            }
        }

        PaginatedDataTable(
            columns = {
                column { Text("Тип") }
                column { Text("Название") }
                column { Text("Сумма") }
                column { Text("Пользователь") }
                column { Text("Событие") }
                column { Text("Дата создания") }
            },
            paginationState = paginationState,
            onPageChanged = {
                viewModel.load((it.pageIndex - 1) * NUMBER_OF_ROWS)

                values
            }
        ) { transaction: Transaction ->
            row(modifier = Modifier) {
                cell { Text(transaction.type.label) }
                cell {
                    Text(
                        modifier = Modifier.clickable {
                            onSelect(Screen.TransactionViewScreen(transaction.uuid))
                        },
                        text = transaction.name
                    )
                }
                cell { Text(transaction.cash.toMoneyPrettyString()) }
                cell {
                    Text(
                        modifier = Modifier.then(
                            transaction.person?.uuid?.let {
                                Modifier.clickable {
                                    onSelect(Screen.UserViewScreen(it))
                                }
                            } ?: Modifier
                        ),
                        text = transaction.person?.name ?: ""
                    )
                }
                cell {
                    Text(
                        modifier = Modifier.then(
                            transaction.event?.uuid?.let {
                                Modifier.clickable {
                                    onSelect(Screen.EventViewScreen(it))
                                }
                            } ?: Modifier
                        ),
                        text = transaction.event?.name ?: ""
                    )
                }
                cell { Text(transaction.transactionDate.toPrettyString()) }

            }
        }
    }
}
