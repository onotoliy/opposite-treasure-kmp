package com.github.onotoliy.opposite.ui.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.components.fields.CalendarField
import com.github.onotoliy.opposite.ui.components.fields.MoneyField
import kotlinx.datetime.Instant
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun EventModificationLayout(
    name: String,
    onNameChange: (String) -> Unit,
    contribution: String,
    onContributionChange: (String) -> Unit,
    isContributionEnable: Boolean,
    deadline: Instant,
    onDeadlineChange: (Instant) -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Название") },
            value = name,
            onValueChange = onNameChange
        )
        MoneyField(
            modifier = Modifier.fillMaxWidth(),
            enabled = isContributionEnable,
            label = "Сумма",
            value = contribution,
            onValueChange = onContributionChange
        )
        CalendarField(
            label = "Сдать до",
            value = deadline,
            onValueChanged = onDeadlineChange
        )
    }
}