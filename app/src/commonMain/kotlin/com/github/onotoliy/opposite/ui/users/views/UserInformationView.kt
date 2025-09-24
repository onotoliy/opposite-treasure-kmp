package com.github.onotoliy.opposite.ui.users.views

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
import com.github.onotoliy.opposite.repositories.name
import com.github.onotoliy.opposite.ui.components.buttons.DeleteButton
import com.github.onotoliy.opposite.ui.components.buttons.DeleteIconButton
import com.github.onotoliy.opposite.ui.components.buttons.EditButton
import com.github.onotoliy.opposite.ui.components.buttons.EditFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.users.UserInformationLayout
import com.github.onotoliy.opposite.ui.users.models.UserViewModel
import com.github.onotoliy.opposite.viewmodel.AbstractInformationView
import org.jetbrains.compose.resources.painterResource
import kotlin.time.ExperimentalTime

@Composable
expect fun UserInformationView(viewModel: UserViewModel)

@OptIn(ExperimentalTime::class)
@Composable
fun UserInformationMobileView(viewModel: UserViewModel) {
    val logo by viewModel.logo.collectAsState()
    val deposit by viewModel.info.collectAsState()
    val navHostController = LocalNavHostController.current

    LocalMobileScafoldState.current.titleTopBar = { Text(deposit.name) }
    LocalMobileScafoldState.current.actionsTopBar = {
        DeleteIconButton {
            viewModel.onDelete(deposit.uuid) {
                navHostController.goto(Screen.UserListScreen)
            }
        }
    }
    LocalMobileScafoldState.current.floatingActionButton = {
        EditFloatingActionButton {
            navHostController.goto(
                Screen.UserEditScreen(deposit.uuid)
            )
        }
    }

    AbstractInformationView(viewModel = viewModel) {
        Column(
            modifier = Modifier.padding(horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            UserInformationLayout(deposit)

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
}

@OptIn(ExperimentalTime::class)
@Composable
fun UserInformationWebView(viewModel: UserViewModel) {
    val logo by viewModel.logo.collectAsState()
    val deposit by viewModel.info.collectAsState()
    val navHostController = LocalNavHostController.current

    AbstractInformationView(viewModel = viewModel) {
        Row(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            ElevatedCard {
                Image(
                    painter = painterResource(logo),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier.size(300.dp)
                )
            }

            Column {
                UserInformationLayout(deposit)

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    EditButton {
                        navHostController.goto(Screen.UserEditScreen(deposit.uuid))
                    }
                    DeleteButton {
                        viewModel.onDelete(deposit.uuid) {
                            navHostController.goto(Screen.UserListScreen)
                        }
                    }
                }
            }
        }
    }
}
