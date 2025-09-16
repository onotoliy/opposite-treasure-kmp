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
import com.github.onotoliy.opposite.repositories.newDeposit
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.SaveButton
import com.github.onotoliy.opposite.ui.components.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.users.UserCreateModel
import kotlinx.datetime.Clock
import kotlin.time.ExperimentalTime

@Composable
expect fun UserCreateView(viewModel: UserCreateModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class)
fun UserCreateMobileView(viewModel: UserCreateModel, onSelect: (Screen) -> Unit) {
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf(Clock.System.now()) }
    var joiningDate by remember { mutableStateOf(Clock.System.now()) }
    var position by remember { mutableStateOf(Deposit.Position.NONE) }

    LocalMobileScafoldState.current.topBar = { Text("Создание мероприятия") }
    LocalMobileScafoldState.current.floatingActionButton = {
        SaveFloatingActionButton {
            viewModel.onSave(
                newDeposit(
                    firstName = firstName,
                    lastName = lastName,
                    patronymic = patronymic,
                    email = email,
                    username = username,
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
        firstName = firstName,
        onFirstNameChanged = { firstName = it },
        lastName = lastName,



        username = username,
        onUsernameChanged = { username = it },
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
    var username by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf(Clock.System.now()) }
    var joiningDate by remember { mutableStateOf(Clock.System.now()) }
    var position by remember { mutableStateOf(Deposit.Position.NONE) }

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
                    newDeposit(
                        firstName = firstName,
                        lastName = lastName,
                        patronymic = patronymic,
                        email = email,
                        username = username,
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
