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
import com.github.onotoliy.opposite.ui.components.buttons.CancelButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveButton
import com.github.onotoliy.opposite.ui.components.buttons.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.UserModificationLayout
import com.github.onotoliy.opposite.ui.users.models.UserEditModel
import com.github.onotoliy.opposite.viewmodel.UiState
import kotlin.time.ExperimentalTime

@Composable
expect fun UserEditView(viewModel: UserEditModel, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
fun UserEditMobileView(viewModel: UserEditModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val data by viewModel.info.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    var username by remember { mutableStateOf(data.username) }
    var firstName by remember { mutableStateOf(data.firstName) }
    var lastName by remember { mutableStateOf(data.lastName) }
    var patronymic by remember { mutableStateOf(data.patronymic) }
    var email by remember { mutableStateOf(data.email) }
    var birthday by remember { mutableStateOf(data.birthday) }
    var joiningDate by remember { mutableStateOf(data.joiningDate) }
    var position by remember { mutableStateOf(data.position) }

    LocalMobileScafoldState.current.topBar = { Text("Изменение мероприятия") }
    LocalMobileScafoldState.current.floatingActionButton = {
        SaveFloatingActionButton {
            viewModel.onSave(
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
            isUsernameEnable = false,
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
fun UserEditWebView(viewModel: UserEditModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val data by viewModel.info.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    var username by remember { mutableStateOf(data.username) }
    var firstName by remember { mutableStateOf(data.firstName) }
    var lastName by remember { mutableStateOf(data.lastName) }
    var patronymic by remember { mutableStateOf(data.patronymic) }
    var email by remember { mutableStateOf(data.email) }
    var birthday by remember { mutableStateOf(data.birthday) }
    var joiningDate by remember { mutableStateOf(data.joiningDate) }
    var position by remember { mutableStateOf(data.position) }

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
            isUsernameEnable = false,
            onUsernameChanged = { username = it },
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
