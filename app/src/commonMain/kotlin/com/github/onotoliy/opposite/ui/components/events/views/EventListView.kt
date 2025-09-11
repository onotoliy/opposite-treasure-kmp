package com.github.onotoliy.opposite.ui.components.events.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.components.AddFloatingActionButton
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.Screen.EventViewScreen
import com.github.onotoliy.opposite.viewmodel.events.EventView
import com.github.onotoliy.opposite.viewmodel.events.EventsListModel
import io.github.windedge.table.DataTable
import kotlin.time.ExperimentalTime

@Composable
expect fun EventListView(onLoadMore: () -> List<Event>, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class)
private fun EventMobileListItem(event: Event, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .clickable(onClick = onClick),
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
                Text(text = "Сумма: ${event.contribution}")
                Text(text = "Сдать до: ${event.deadline.toString()}")
            }

            HorizontalDivider()
        }
    }
}

@Composable
@OptIn(ExperimentalTime::class)
fun EventListMobileView(onLoadMore: () -> List<Event>, onSelect: (Screen) -> Unit) {
    var events by remember { mutableStateOf(emptyList<Event>()) }
    var isLoading by remember { mutableStateOf(false) }

    val listState = rememberLazyListState()
    val loadMoreThreshold = 3

    LaunchedEffect(Unit) {
        isLoading = true
        events = events + onLoadMore()
        isLoading = false
    }

    LocalMobileScafoldState.current.floatingActionButton = {
        AddFloatingActionButton { onSelect(Screen.EventNewScreen) }
    }

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(events) { index, event ->
            EventMobileListItem(event) { onSelect(EventViewScreen(event.uuid)) }

            if (!isLoading && index >= events.lastIndex - loadMoreThreshold) {
                LaunchedEffect(index) {
                    isLoading = true
                    events = events + onLoadMore()
                    isLoading = false
                }
            }
        }

        if (isLoading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalTime::class)
fun EventTableWebView(onLoadMore: () -> List<Event>, onSelect: (Screen) -> Unit) {
    var events by remember { mutableStateOf(emptyList<Event>()) }
    var isLoading by remember { mutableStateOf(false) }

    // первая загрузка
    LaunchedEffect(Unit) {
        isLoading = true
        events = onLoadMore()
        isLoading = false
    }

    Column {
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

        Box(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Button(onClick = {
                    isLoading = true
                    val newItems = onLoadMore()
                    events = events + newItems
                    isLoading = false
                }) {
                    Text("Загрузить ещё")
                }
            }
        }
    }
}
