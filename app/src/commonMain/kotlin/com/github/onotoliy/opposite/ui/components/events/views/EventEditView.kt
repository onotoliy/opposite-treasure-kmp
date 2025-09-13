package com.github.onotoliy.opposite.ui.components.events.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.components.CancelButton
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.SaveButton
import com.github.onotoliy.opposite.ui.components.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.events.EventModificationLayout
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import kotlin.time.ExperimentalTime

@Composable
expect fun EventEditView(viewModel: EventEditModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class)
fun EventEditMobileView(viewModel: EventEditModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.state.collectAsState()

    UiStateScreen<Event>(state, load = viewModel::load) { data ->
        var name by remember { mutableStateOf(data.name) }
        var contribution by remember { mutableStateOf(data.contribution) }
        var deadline by remember { mutableStateOf(data.deadline) }

        LocalMobileScafoldState.current.topBar = { Text("Изменение мероприятия") }
        LocalMobileScafoldState.current.floatingActionButton = {
            SaveFloatingActionButton {
                viewModel.onSave(data.copy(name = name, deadline = deadline)) {
                    onSelect(Screen.EventViewScreen(it.uuid))
                }
            }
        }

        EventModificationLayout(
            name = name,
            onNameChange = { name = it },
            contribution = contribution,
            onContributionChange = { contribution = it },
            isContributionEnable = false,
            deadline = deadline,
            onDeadlineChange = { deadline = it }
        )
    }
}

@Composable
@OptIn(ExperimentalTime::class)
fun EventEditWebView(viewModel: EventEditModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.state.collectAsState()

    UiStateScreen<Event>(state, load = viewModel::load) { data ->
        var name by remember { mutableStateOf(data.name) }
        var contribution by remember { mutableStateOf(data.contribution) }
        var deadline by remember { mutableStateOf(data.deadline) }

        Column(
            modifier = Modifier.padding(horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            EventModificationLayout(
                name = name,
                onNameChange = { name = it },
                contribution = contribution,
                onContributionChange = { contribution = it },
                isContributionEnable = false,
                deadline = deadline,
                onDeadlineChange = { deadline = it }
            )

            Row {
                SaveButton {
                    viewModel.onSave(data.copy(name = name, deadline = deadline)) {
                        onSelect(Screen.EventViewScreen(it.uuid))
                    }
                }
                CancelButton {
                    onSelect(Screen.EventsScreen)
                }
            }
        }
    }
}