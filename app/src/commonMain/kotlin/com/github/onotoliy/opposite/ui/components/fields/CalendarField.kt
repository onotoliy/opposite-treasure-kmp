package com.github.onotoliy.opposite.ui.components.fields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
private fun Instant?.toPrettyString(): String {
    return this?.toString()?.substring(0, 10) ?: ""
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun CalendarField(
    value: Instant = Clock.System.now(),
    label: String = "Дата",
    onValueChanged: (Instant) -> Unit,
) {
    var open by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = value.toEpochMilliseconds()
    )

    val selectedDateInstant = datePickerState.selectedDateMillis?.let {
        Instant.fromEpochMilliseconds(it)
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedDateInstant.toPrettyString(),
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Box(modifier = Modifier.matchParentSize().clickable { open = true })
    }

    if (open) {
        DatePickerDialog(
            onDismissRequest = { open = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        onValueChanged(Instant.fromEpochMilliseconds(millis))
                    }

                    open = false
                }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { open = false }) { Text("Отмена") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}