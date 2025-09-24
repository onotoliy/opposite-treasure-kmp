package com.github.onotoliy.opposite.ui.events.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.repositories.newEvent
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.ui.components.buttons.CancelButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.events.EventModificationLayout
import com.github.onotoliy.opposite.ui.events.models.EventCreateModel
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.viewmodel.AbstractCreateView
import kotlinx.datetime.Clock
import kotlin.time.ExperimentalTime

@Composable
expect fun EventCreateView(viewModel: EventCreateModel)

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun EventCreateMobileView(viewModel: EventCreateModel) {
    val navHostController = LocalNavHostController.current

    LocalMobileScafoldState.current.titleTopBar = { Text("Создание мероприятия") }

    BaseEventEditView(viewModel) { event ->
        LocalMobileScafoldState.current.floatingActionButton = {
            SaveFloatingActionButton {
                viewModel.onSave(event) {
                    navHostController.goto(Screen.UserViewScreen(it.uuid))
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun EventCreateWebView(viewModel: EventCreateModel) {
    val navHostController = LocalNavHostController.current

    BaseEventEditView(viewModel) { event ->
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
private fun BaseEventEditView(
    viewModel: EventCreateModel,
    actions: @Composable (event: Event) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var contribution by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf(Clock.System.now()) }

    AbstractCreateView(viewModel) {
        EventModificationLayout(
            name = name,
            onNameChange = { name = it },
            contribution = contribution,
            onContributionChange = { contribution = it },
            isContributionEnable = true,
            deadline = deadline,
            onDeadlineChange = { deadline = it }
        )

        actions(
            newEvent(name = name, contribution = contribution, deadline = deadline)
        )
    }
}
