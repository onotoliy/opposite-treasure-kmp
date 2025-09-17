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
import com.github.onotoliy.opposite.repositories.lablel
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.treasure.model.Option
import com.github.onotoliy.opposite.ui.CalendarField
import com.github.onotoliy.opposite.ui.DropdownMenu
import com.github.onotoliy.opposite.ui.LabelledText
import kotlinx.datetime.Instant
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
@Composable
fun UserModificationLayout(deposit: Deposit) {
    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LabelledText("Фамилия", deposit.lastName)
        LabelledText("Имя", deposit.firstName)
        LabelledText("Отчество", deposit.patronymic)
        LabelledText("Номер телефона", deposit.username)
        LabelledText("Электронная почта", deposit.email)
        LabelledText("Должность", deposit.position.label)
        LabelledText("День рождение", deposit.birthday)
        LabelledText("Дата вступления", deposit.joiningDate)
    }
}