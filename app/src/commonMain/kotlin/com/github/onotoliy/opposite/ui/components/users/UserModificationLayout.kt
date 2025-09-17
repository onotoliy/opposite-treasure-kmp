package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.repositories.label
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.treasure.model.Option
import com.github.onotoliy.opposite.ui.CalendarField
import com.github.onotoliy.opposite.ui.DropdownMenu
import kotlinx.datetime.Instant
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
@Composable
fun UserModificationLayout(
    firstName: String,
    onFirstNameChanged: (String) -> Unit,

    lastName: String,
    onLastNameChanged: (String) -> Unit,

    patronymic: String,
    onPatronymicChanged: (String) -> Unit,

    email: String,
    onEmailChanged: (String) -> Unit,

    username: String,
    isUsernameEnable: Boolean,
    onUsernameChanged: (String) -> Unit,

    birthday: Instant,
    onBirthdayChanged: (Instant) -> Unit,

    joiningDate: Instant,
    onJoiningDateChanged: (Instant) -> Unit,

    position: Deposit.Position,
    onPositionChanged: (Deposit.Position) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Фамилия") },
            value = lastName,
            onValueChange = onLastNameChanged
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Имя") },
            value = firstName,
            onValueChange = onFirstNameChanged
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Отчество") },
            value = patronymic,
            onValueChange = onPatronymicChanged
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Номер телефона") },
            value = username,
            enabled = isUsernameEnable,
            onValueChange = onUsernameChanged
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Электронная почта") },
            value = email,
            onValueChange = onEmailChanged
        )

        DropdownMenu(
            label = "Должность",
            enabled = true,
            value = Option(position.name, position.label),
            options = Deposit.Position.values().map { Option(it.name, it.label) },
            onValueChange = { onPositionChanged(Deposit.Position.valueOf(it.uuid)) }
        )

        CalendarField(
            label = "День рождение",
            value = birthday,
            onValueChanged = onBirthdayChanged
        )

        CalendarField(
            label = "Дата вступления",
            value = joiningDate,
            onValueChanged = onJoiningDateChanged
        )
    }
}