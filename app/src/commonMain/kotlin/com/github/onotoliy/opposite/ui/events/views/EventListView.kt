package com.github.onotoliy.opposite.ui.events.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Event
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
import com.github.onotoliy.opposite.repositories.name
import com.github.onotoliy.opposite.repositories.toMoneyPrettyString
import com.github.onotoliy.opposite.repositories.toPrettyString
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.ui.components.buttons.AddButton
import com.github.onotoliy.opposite.ui.components.buttons.AddFloatingActionButton
import com.github.onotoliy.opposite.ui.components.infinity.ListInfinity
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.events.models.EventListAdapter
import com.github.onotoliy.opposite.ui.events.models.EventListModel
import com.github.onotoliy.opposite.ui.events.models.EventTableModel
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.Screen.EventViewScreen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.viewmodel.AbstractTableView
import com.github.onotoliy.opposite.viewmodel.UiState
import io.github.windedge.table.DataTable
import io.github.windedge.table.m3.PaginatedDataTable
import io.github.windedge.table.rememberPaginationState
import kotlin.time.ExperimentalTime

@Composable
expect fun EventListView(listAdapter: EventListAdapter, hasActionButtons: Boolean)

@Composable
@OptIn(ExperimentalTime::class)
fun EventListMobileView(viewModel: EventListModel, hasActionButtons: Boolean) {
    val navHostController = LocalNavHostController.current


    LocalMobileScafoldState.current.floatingActionButton = {
        AddFloatingActionButton { navHostController.goto(Screen.EventNewScreen) }
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .clickable { navHostController.goto(EventViewScreen(event.uuid)) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Outlined.Event, contentDescription = null)

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(text = event.name)

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Сумма: ${event.contribution.toMoneyPrettyString()}")
                    Text(text = "Сдать до: ${event.deadline.toPrettyString()}")
                }

                HorizontalDivider()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalTime::class)
fun EventTableWebView(viewModel: EventTableModel, hasActionButtons: Boolean) {
    val navHostController = LocalNavHostController.current
    val total by viewModel.total.collectAsState()
    val page = rememberPaginationState(total, pageSize = NUMBER_OF_ROWS)

    AbstractTableView(viewModel) {
        if (hasActionButtons) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                AddButton {
                    navHostController.goto(Screen.EventNewScreen)
                }
            }
        }

        PaginatedDataTable(
            columns = {
                column { Text("Название") }
                column { Text("Сумма") }
                column { Text("Сдать до") }
            },
            paginationState = page,
            onPageChanged = {
                viewModel.sload((it.pageIndex - 1) * NUMBER_OF_ROWS)
            }
        ) { event: Event ->
            row(modifier = Modifier) {
                cell {
                    Text(
                        modifier = Modifier.clickable {
                            navHostController.goto(EventViewScreen(event.uuid))
                        },
                        text = event.name
                    )
                }
                cell { Text(event.contribution.toMoneyPrettyString()) }
                cell { Text(event.deadline.toPrettyString()) }
            }
        }
    }
}
