package com.github.onotoliy.opposite.ui.events

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventsListModel
import io.github.windedge.table.DataTable
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EventsTableScreen(model: EventsListModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    val state by model.state.collectAsState()

    UiStateScreen<List<Event>>(state, load = model::load) { events ->
        DataTable(
            columns = {
                headerBackground {
                    Box(modifier = Modifier.background(color = Color.LightGray))
                }
                column { Text("Название") }
                column { Text("Сумма") }
                column { Text("Сдать до") }
                column { Text("Автор") }
                column { Text("Дата создания") }
            }
        ) {
            events.forEach { record ->
                row(modifier = Modifier) {
                    cell {
                        Text(modifier = Modifier.clickable(onClick = {
                            onSelect(
                                Screen.EventViewScreen(
                                    record.uuid
                                )
                            )
                        }), text = record.name)
                    }
                    cell { Text(record.contribution) }
                    cell { Text(record.deadline) }
                    cell { Text(record.author.name) }
                    cell { Text(record.creationDate) }
                }

            }
        }
    }
}
