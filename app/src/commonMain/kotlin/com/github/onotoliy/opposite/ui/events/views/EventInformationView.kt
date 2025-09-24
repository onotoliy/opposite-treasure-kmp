package com.github.onotoliy.opposite.ui.events.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.components.buttons.DeleteButton
import com.github.onotoliy.opposite.ui.components.buttons.DeleteIconButton
import com.github.onotoliy.opposite.ui.components.buttons.EditButton
import com.github.onotoliy.opposite.ui.components.buttons.EditFloatingActionButton
import com.github.onotoliy.opposite.ui.events.EventInformationLayout
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.events.models.EventViewModel
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.viewmodel.AbstractInformationView
import org.jetbrains.compose.resources.painterResource
import kotlin.time.ExperimentalTime

@Composable
expect fun EventInformationView(viewModel: EventViewModel)

@Composable
@OptIn(ExperimentalTime::class)
fun EventInformationMobileView(viewModel: EventViewModel) {
    val event by viewModel.info.collectAsState()
    val logo by viewModel.logo.collectAsState()
    val navHostController = LocalNavHostController.current

    LocalMobileScafoldState.current.titleTopBar = { Text(event.name) }
    LocalMobileScafoldState.current.floatingActionButton = {
        EditFloatingActionButton {
            navHostController.goto(
                Screen.EventEditScreen(event.uuid)
            )
        }
    }
    LocalMobileScafoldState.current.actionsTopBar = {
        DeleteIconButton {
            viewModel.onDelete(event.uuid) {
                navHostController.goto(Screen.TransactionListScreen)
            }
        }
    }

    AbstractInformationView(viewModel = viewModel) {
        EventInformationLayout(event, navHostController::goto)

        ElevatedCard(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Image(
                painter = painterResource(logo),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier.size(300.dp)
            )
        }
    }
}

@Composable
@OptIn(ExperimentalTime::class)
fun EventInformationWebView(viewModel: EventViewModel) {
    val event by viewModel.info.collectAsState()
    val logo by viewModel.logo.collectAsState()
    val navHostController = LocalNavHostController.current

    AbstractInformationView(viewModel = viewModel) {
        Row(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(logo),
                contentDescription = null,
                alignment = Alignment.TopCenter,
                modifier = Modifier.size(300.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                EventInformationLayout(event, navHostController::goto)

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp),) {
                    EditButton {
                        navHostController.goto(Screen.EventEditScreen(event.uuid))
                    }

                    DeleteButton {
                        viewModel.onDelete(event.uuid) {
                            navHostController.goto(Screen.EventListScreen)
                        }
                    }
                }
            }
        }
    }
}
