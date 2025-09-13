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
import com.github.onotoliy.opposite.data.Option
import com.github.onotoliy.opposite.data.TransactionType
import com.github.onotoliy.opposite.ui.CalendarField
import com.github.onotoliy.opposite.ui.DropdownMenu
import com.github.onotoliy.opposite.ui.SwaggestBox
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
@Composable
fun UserModificationLayout(
    name: String,
    onNameChanged: (String) -> Unit,

    login: String,
    onLoginChanged: (String) -> Unit,

    birthday: Instant,
    onBirthdayChanged: (Instant) -> Unit,

    joiningDate: Instant,
    onJoiningDateChanged: (Instant) -> Unit,

    position: String,
    onPositionChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("ФИО") },
            value = name,
            onValueChange = onNameChanged
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Логин") },
            value = login,
            onValueChange = onLoginChanged
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Должность") },
            value = position,
            onValueChange = onPositionChanged
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