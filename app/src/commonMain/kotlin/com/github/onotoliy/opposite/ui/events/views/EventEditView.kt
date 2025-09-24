package com.github.onotoliy.opposite.ui.events.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.ui.components.buttons.CancelButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.events.EventModificationLayout
import com.github.onotoliy.opposite.ui.events.models.EventEditModel
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.viewmodel.AbstractEditView
import kotlin.time.ExperimentalTime

@Composable
expect fun EventEditView(viewModel: EventEditModel)

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun EventEditMobileView(viewModel: EventEditModel) {
    val navHostController = LocalNavHostController.current

    LocalMobileScafoldState.current.titleTopBar = { Text("Изменение мероприятия") }

    BaseEventEditWebView(viewModel) { event ->
        LocalMobileScafoldState.current.floatingActionButton = {
            SaveFloatingActionButton {
                viewModel.onSave(event) {
                    navHostController.goto(Screen.EventViewScreen(it.uuid))
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun EventEditWebView(viewModel: EventEditModel) {
    val navHostController = LocalNavHostController.current

    BaseEventEditWebView(viewModel) { event ->
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SaveButton {
                viewModel.onSave(event) {
                    navHostController.goto(Screen.EventViewScreen(event.uuid))
                }
            }
            CancelButton {
                navHostController.goto(Screen.EventListScreen)
            }
        }
    }
}


@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
private fun BaseEventEditWebView(
    viewModel: EventEditModel,
    actions: @Composable (event: Event) -> Unit
) {
    val data by viewModel.info.collectAsState()

    var name by remember { mutableStateOf(data.name) }
    var contribution by remember { mutableStateOf(data.contribution) }
    var deadline by remember { mutableStateOf(data.deadline) }

    LaunchedEffect(data) {
        name = data.name
        contribution = data.contribution
        deadline = data.deadline
    }

    AbstractEditView(viewModel) {
        EventModificationLayout(
            name = name,
            onNameChange = { name = it },
            contribution = contribution,
            onContributionChange = { contribution = it },
            isContributionEnable = false,
            deadline = deadline,
            onDeadlineChange = { deadline = it }
        )

        actions(
            data.copy(name = name, deadline = deadline)
        )
    }
}
