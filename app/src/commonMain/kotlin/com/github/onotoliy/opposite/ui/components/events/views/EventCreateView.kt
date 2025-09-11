package com.github.onotoliy.opposite.ui.components.events.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.SaveButton
import com.github.onotoliy.opposite.ui.components.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.events.EventEditForm
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventNewViewModel
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Composable
expect fun EventCreateView(viewModel: EventNewViewModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class)
fun EventCreateMobileView(viewModel: EventNewViewModel, onSelect: (Screen) -> Unit) {
    var name by remember { mutableStateOf("") }
    var contribution by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf(Clock.System.now()) }

    LocalMobileScafoldState.current.topBar = { Text("Создание мероприятия") }
    LocalMobileScafoldState.current.floatingActionButton = {
        SaveFloatingActionButton {
            viewModel.onSave(name, contribution, deadline) {
                onSelect(Screen.EventViewScreen(it.uuid))
            }
        }
    }

    EventEditForm(
        name = name,
        onNameChange = { name = it },
        contribution = contribution,
        onContributionChange = { contribution = it },
        isContributionEnable = true,
        deadline = deadline,
        onDeadlineChange = { deadline = it }
    )
}

@Composable
@OptIn(ExperimentalTime::class)
fun EventCreateWebView(viewModel: EventNewViewModel, onSelect: (Screen) -> Unit) {
    var name by remember { mutableStateOf("") }
    var contribution by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf(Clock.System.now()) }

    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        EventEditForm(
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
                viewModel.onSave(name, contribution, deadline) {
                    onSelect(Screen.EventViewScreen(it.uuid))
                }
            }
            SaveButton {
                onSelect(Screen.EventsScreen)
            }
        }
    }
}