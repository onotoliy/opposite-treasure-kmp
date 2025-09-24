package com.github.onotoliy.opposite.ui.users.views

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
import com.github.onotoliy.opposite.repositories.newDeposit
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.ui.components.buttons.CancelButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.users.UserModificationLayout
import com.github.onotoliy.opposite.ui.users.models.UserCreateModel
import com.github.onotoliy.opposite.viewmodel.AbstractCreateView
import kotlinx.datetime.Clock
import kotlin.time.ExperimentalTime

@Composable
expect fun UserCreateView(viewModel: UserCreateModel)

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun UserCreateMobileView(viewModel: UserCreateModel) {
    val navHostController = LocalNavHostController.current

    LocalMobileScafoldState.current.titleTopBar = { Text("Создание пользователя") }

    BaseUserCreateView(viewModel) { newDeposit ->
        LocalMobileScafoldState.current.floatingActionButton = {
            SaveFloatingActionButton {
                viewModel.onSave(newDeposit) {
                    navHostController.goto(Screen.UserViewScreen(it.uuid))
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun UserCreateWebView(viewModel: UserCreateModel) {
    val navHostController = LocalNavHostController.current

    BaseUserCreateView(viewModel) { newDeposit ->
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            SaveButton {
                viewModel.onSave(newDeposit) {
                    navHostController.goto(Screen.UserViewScreen(it.uuid))
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
private fun BaseUserCreateView(
    viewModel: UserCreateModel,
    actions: @Composable (deposit: Deposit) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf(Clock.System.now()) }
    var joiningDate by remember { mutableStateOf(Clock.System.now()) }
    var position by remember { mutableStateOf(Deposit.Position.NONE) }

    AbstractCreateView(viewModel) {
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
            onUsernameChanged = { username = it },
            isUsernameEnable = true,
            birthday = birthday,
            onBirthdayChanged = { birthday = it },
            joiningDate = joiningDate,
            onJoiningDateChanged = { joiningDate = it },
            position = position,
            onPositionChanged = { position = it }
        )

        actions(
            newDeposit(
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