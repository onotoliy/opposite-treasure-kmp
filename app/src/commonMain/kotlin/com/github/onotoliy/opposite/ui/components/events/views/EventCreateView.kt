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
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.treasure.model.Option
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.SaveButton
import com.github.onotoliy.opposite.ui.components.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.events.EventModificationLayout
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventCreateModel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Composable
expect fun EventCreateView(viewModel: EventCreateModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class)
fun EventCreateMobileView(viewModel: EventCreateModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()

    UiStateScreen(state) {
        var name by remember { mutableStateOf("") }

        var contribution by remember { mutableStateOf("") }
        var deadline by remember { mutableStateOf(Clock.System.now()) }

        LocalMobileScafoldState.current.topBar = { Text("Создание мероприятия") }
        LocalMobileScafoldState.current.floatingActionButton = {
            SaveFloatingActionButton {
                viewModel.onSave(newEvent(name, contribution, deadline)) {
                    onSelect(Screen.EventViewScreen(it.uuid))
                }
            }
        }

        EventModificationLayout(
            name = name,
            onNameChange = { name = it },
            contribution = contribution,
            onContributionChange = { contribution = it },
            isContributionEnable = true,
            deadline = deadline,
            onDeadlineChange = { deadline = it }
        )
    }
}

@Composable
@OptIn(ExperimentalTime::class)
fun EventCreateWebView(viewModel: EventCreateModel, onSelect: (Screen) -> Unit) {
    var name by remember { mutableStateOf("") }
    var contribution by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf(Clock.System.now()) }

    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        EventModificationLayout(
            name = name,
            onNameChange = { name = it },
            contribution = contribution,
            onContributionChange = { contribution = it },
            isContributionEnable = true,
            deadline = deadline,
            onDeadlineChange = { deadline = it }
        )

        Row {
            SaveButton {
                viewModel.onSave(newEvent(name, contribution, deadline)) {
                    onSelect(Screen.EventViewScreen(it.uuid))
                }
            }
            SaveButton {
                onSelect(Screen.EventsScreen)
            }
        }
    }
}

@OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
fun newEvent(name: String, contribution: String, deadline: Instant): Event = Event(
    uuid = Uuid.random().toString(),
    name = name,
    contribution = contribution,
    deadline = deadline,
    creationDate = Clock.System.now(),
    author = Option("", ""),
    deletionDate = null
)
