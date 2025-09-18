package com.github.onotoliy.opposite.ui.users.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.repositories.newDeposit
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.ui.components.buttons.SaveButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.UserModificationLayout
import com.github.onotoliy.opposite.ui.users.models.UserCreateModel
import com.github.onotoliy.opposite.viewmodel.UiState
import kotlinx.datetime.Clock
import kotlin.time.ExperimentalTime

@Composable
expect fun UserCreateView(viewModel: UserCreateModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun UserCreateMobileView(viewModel: UserCreateModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    var username by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf(Clock.System.now()) }
    var joiningDate by remember { mutableStateOf(Clock.System.now()) }
    var position by remember { mutableStateOf(Deposit.Position.NONE) }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    LocalMobileScafoldState.current.topBar = { Text("Создание пользователя") }
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

    Column {
        when (state) {
            is UiState.Error -> {}
            UiState.Loading -> LinearProgressIndicator(Modifier.fillMaxWidth())
            is UiState.Success -> {}
        }

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
    }
}

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun UserCreateWebView(viewModel: UserCreateModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    var username by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf(Clock.System.now()) }
    var joiningDate by remember { mutableStateOf(Clock.System.now()) }
    var position by remember { mutableStateOf(Deposit.Position.NONE) }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        when (state) {
            is UiState.Error -> {}
            UiState.Loading -> LinearProgressIndicator(Modifier.fillMaxWidth())
            is UiState.Success -> {}
        }

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
