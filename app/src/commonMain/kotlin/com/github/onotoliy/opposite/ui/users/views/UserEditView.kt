package com.github.onotoliy.opposite.ui.users.views

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
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.ui.components.buttons.CancelButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.users.UserModificationLayout
import com.github.onotoliy.opposite.ui.users.models.UserEditModel
import com.github.onotoliy.opposite.viewmodel.AbstractEditView
import kotlin.time.ExperimentalTime

@Composable
expect fun UserEditView(viewModel: UserEditModel)

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun UserEditMobileView(viewModel: UserEditModel) {
    val navHostController = LocalNavHostController.current

    LocalMobileScafoldState.current.titleTopBar = { Text("Изменение мероприятия") }

    BaseUserEditView(viewModel) { deposit ->
        LocalMobileScafoldState.current.floatingActionButton = {
            SaveFloatingActionButton {
                viewModel.onSave(deposit) {
                    navHostController.goto(Screen.UserViewScreen(it.uuid))
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun UserEditWebView(viewModel: UserEditModel) {
    val navHostController = LocalNavHostController.current

    BaseUserEditView(viewModel) { deposit ->
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SaveButton {
                viewModel.onSave(deposit) {
                    navHostController.goto(Screen.UserViewScreen(deposit.uuid))
                }
            }
            CancelButton {
                navHostController.goto(Screen.UserListScreen)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
private fun BaseUserEditView(
    viewModel: UserEditModel,
    actions: @Composable (deposit: Deposit) -> Unit
) {
    val data by viewModel.info.collectAsState()

    var username by remember { mutableStateOf(data.username) }
    var firstName by remember { mutableStateOf(data.firstName) }
    var lastName by remember { mutableStateOf(data.lastName) }
    var patronymic by remember { mutableStateOf(data.patronymic) }
    var email by remember { mutableStateOf(data.email) }
    var birthday by remember { mutableStateOf(data.birthday) }
    var joiningDate by remember { mutableStateOf(data.joiningDate) }
    var position by remember { mutableStateOf(data.position) }

    LaunchedEffect(data) {
        username = data.username
        firstName = data.firstName
        lastName = data.lastName
        patronymic = data.patronymic
        email = data.email
        birthday = data.birthday
        joiningDate = data.joiningDate
        position = data.position
    }

    AbstractEditView(viewModel) {
        UserModificationLayout(
            firstName = firstName,
            onFirstNameChanged = { firstName = it },
            lastName = lastName,
            onLastNameChanged = { lastName = it },
            patronymic = patronymic,
            onPatronymicChanged = { patronymic = it },
            email = email,
            onEmailChanged = { email = it },
            username = username,
            isUsernameEnable = false,
            onUsernameChanged = { username = it },
            birthday = birthday,
            onBirthdayChanged = { birthday = it },
            joiningDate = joiningDate,
            onJoiningDateChanged = { joiningDate = it },
            position = position,
            onPositionChanged = { position = it }
        )

        actions(
            data.copy(
                username = username,
                firstName = firstName,
                lastName = lastName,
                patronymic = patronymic,
                email = email,
                birthday = birthday,
                joiningDate = joiningDate,
                position = position
            )
        )
    }
}
