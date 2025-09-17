package com.github.onotoliy.opposite.ui.events.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.components.ErrorMessage
import com.github.onotoliy.opposite.ui.components.buttons.CancelButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.events.EventModificationLayout
import com.github.onotoliy.opposite.ui.events.models.EventEditModel
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.UiState
import kotlin.time.ExperimentalTime

@Composable
expect fun EventEditView(viewModel: EventEditModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class)
fun EventEditMobileView(viewModel: EventEditModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val data by viewModel.info.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

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

    Column {
        when (state) {
            is UiState.Error -> ErrorMessage((state as UiState.Error).message)
            UiState.Loading -> LinearProgressIndicator()
            is UiState.Success -> {}
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
    val state by viewModel.loadState.collectAsState()
    val data by viewModel.info.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    var name by remember { mutableStateOf(data.name) }
    var contribution by remember { mutableStateOf(data.contribution) }
    var deadline by remember { mutableStateOf(data.deadline) }

    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when (state) {
            is UiState.Error -> ErrorMessage((state as UiState.Error).message)
            UiState.Loading -> LinearProgressIndicator()
            is UiState.Success -> {}
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
