package com.github.onotoliy.opposite.ui.components.users

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
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.components.CancelButton
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.SaveButton
import com.github.onotoliy.opposite.ui.components.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.users.UserEditModel
import kotlin.time.ExperimentalTime

@Composable
expect fun UserEditView(viewModel: UserEditModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class)
fun UserEditMobileView(viewModel: UserEditModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val data by viewModel.info.collectAsState()

    UiStateScreen(state, load = viewModel::load) {
        var name by remember { mutableStateOf(data.name) }
        var login by remember { mutableStateOf(data.username) }
        var birthday by remember { mutableStateOf(data.birthday) }
        var joiningDate by remember { mutableStateOf(data.joiningDate) }
        var position by remember { mutableStateOf(data.position) }

        LocalMobileScafoldState.current.topBar = { Text("Изменение мероприятия") }
        LocalMobileScafoldState.current.floatingActionButton = {
            SaveFloatingActionButton {
                viewModel.onSave(
                    data.copy(
                        name = name,
                        login = login,
                        birthday = birthday,
                        joiningDate = joiningDate,
                        position = position
                    )
                ) {
                    onSelect(Screen.UserViewScreen(it.uuid))
                }
            }
        }

        UserModificationLayout(
            name = name,
            onNameChanged = { name = it },
            username = login,
            onUsernameChanged = { login = it },
            birthday = birthday,
            onBirthdayChanged = { birthday = it },
            joiningDate = joiningDate,
            onJoiningDateChanged = { joiningDate = it },
            position = position,
            onPositionChanged = { position = it },
        )
    }
}

@Composable
@OptIn(ExperimentalTime::class)
fun UserEditWebView(viewModel: UserEditModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val data by viewModel.info.collectAsState()

    UiStateScreen(state, load = viewModel::load) {
        var name by remember { mutableStateOf(data.name) }
        var login by remember { mutableStateOf(data.login) }
        var birthday by remember { mutableStateOf(data.birthday) }
        var joiningDate by remember { mutableStateOf(data.joiningDate) }
        var position by remember { mutableStateOf(data.position) }

        Column(
            modifier = Modifier.padding(horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            UserModificationLayout(
                name = name,
                onNameChanged = { name = it },
                username = login,
                onUsernameChanged = { login = it },
                birthday = birthday,
                onBirthdayChanged = { birthday = it },
                joiningDate = joiningDate,
                onJoiningDateChanged = { joiningDate = it },
                position = position,
                onPositionChanged = { position = it },
            )

            Row {
                SaveButton {
                    viewModel.onSave(
                        data.copy(
                            name = name,
                            login = login,
                            birthday = birthday,
                            joiningDate = joiningDate,
                            position = position
                        )
                    ) {
                        onSelect(Screen.UserViewScreen(it.uuid))
                    }
                }
                CancelButton {
                    onSelect(Screen.EventsScreen)
                }
            }
        }
    }
}