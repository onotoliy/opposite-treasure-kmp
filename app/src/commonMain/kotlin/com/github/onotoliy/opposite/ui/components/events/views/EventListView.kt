package com.github.onotoliy.opposite.ui.components.events.views

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.repositories.toMoneyPrettyString
import com.github.onotoliy.opposite.repositories.toPrettyString
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.ui.components.AddFloatingActionButton
import com.github.onotoliy.opposite.ui.components.ListInfinity
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.TableInfinity
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.Screen.EventViewScreen
import com.github.onotoliy.opposite.viewmodel.events.EventListModel
import io.github.windedge.table.DataTable
import kotlin.time.ExperimentalTime

@Composable
expect fun EventListView(viewModel: EventListModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class)
fun EventListMobileView(viewModel: EventListModel, onSelect: (Screen) -> Unit) {
    LocalMobileScafoldState.current.floatingActionButton = {
        AddFloatingActionButton { onSelect(Screen.EventNewScreen) }
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
                .clickable { onSelect(EventViewScreen(event.uuid)) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Outlined.Event, contentDescription = null)

            Column(modifier = Modifier
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
fun EventTableWebView(viewModel: EventListModel, onSelect: (Screen) -> Unit) {
    TableInfinity<Event>(viewModel) { events ->
        DataTable(
            columns = {
                headerBackground {
                    Box(modifier = Modifier.background(Color.LightGray))
                }
                column { Text("Название") }
                column { Text("Сумма") }
                column { Text("Сдать до") }
            }
        ) {
            events.forEach { record ->
                row {
                    cell {
                        Text(
                            modifier = Modifier.clickable {
                                onSelect(EventViewScreen(record.uuid))
                            },
                            text = record.name
                        )
                    }
                    cell { Text(record.contribution) }
                    cell { Text(record.deadline.toString()) }
                }
            }
        }
    }
}
