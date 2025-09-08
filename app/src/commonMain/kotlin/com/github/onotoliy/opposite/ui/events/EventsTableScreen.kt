package com.github.onotoliy.opposite.ui.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.components.EventTable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventsListModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EventsTableScreen(model: EventsListModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    val state by model.state.collectAsState()

    UiStateScreen<List<Event>>(state, load = model::load) { events ->
        EventsTableScreen(events, onSelect)
    }
}

@Composable
fun EventsTableScreen(events: List<Event>, onSelect: (Screen) -> Unit) {
    EventTable(events, onSelect)
}
