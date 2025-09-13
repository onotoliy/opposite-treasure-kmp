package com.github.onotoliy.opposite.ui.components.users

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
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.SaveButton
import com.github.onotoliy.opposite.ui.components.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.users.UserCreateModel
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Composable
expect fun UserCreateView(viewModel: UserCreateModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class)
fun UserCreateMobileView(viewModel: UserCreateModel, onSelect: (Screen) -> Unit) {
    var name by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf(Clock.System.now()) }
    var joiningDate by remember { mutableStateOf(Clock.System.now()) }
    var position by remember { mutableStateOf("") }

    LocalMobileScafoldState.current.topBar = { Text("Создание мероприятия") }
    LocalMobileScafoldState.current.floatingActionButton = {
        SaveFloatingActionButton {
            viewModel.onSave(
                newUser(
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
        login = login,
        onLoginChanged = { login = it },
        birthday = birthday,
        onBirthdayChanged = { birthday = it },
        joiningDate = joiningDate,
        onJoiningDateChanged = { joiningDate = it },
        position = position,
        onPositionChanged = { position = it },
    )
}

@Composable
@OptIn(ExperimentalTime::class)
fun UserCreateWebView(viewModel: UserCreateModel, onSelect: (Screen) -> Unit) {
    var name by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf(Clock.System.now()) }
    var joiningDate by remember { mutableStateOf(Clock.System.now()) }
    var position by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        UserModificationLayout(
            name = name,
            onNameChanged = { name = it },
            login = login,
            onLoginChanged = { login = it },
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
                    newUser(
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
            SaveButton {
                onSelect(Screen.UsersScreen)
            }
        }
    }
}

@OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)
fun newUser(
    login: String,
    name: String,
    birthday: Instant,
    joiningDate: Instant,
    position: String
) = User(
    uuid = Uuid.random().toString(),
    name = name,
    birthday = birthday,
    joiningDate = joiningDate,
    position = position,
    logo = "",
    login = login,
    deposit = ""
)