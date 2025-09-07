package com.github.onotoliy.opposite.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun DatePickerDialog1() {
    val s = "Длинный StakTrase ошибка\nДлинный StakTrase ошибка\nДлинный StakTrase ошибка\nДлинный StakTrase ошибка\n"


//    SwaggestBox(
//        label="События",
//        onSelected = {
//            println(it)
//        },
//        onQueryChanged = { q ->
//            println(q)
//            delay(1000)
//
//            if (q.isEmpty()) {
//                EventCacheRepository.EVENTS.map { Option(it.uuid, it.name) }
//            } else {
//                EventCacheRepository.EVENTS.map { Option(it.uuid, it.name) }
//                    .filter { it.name.contains(q) }
//            }
//        }
//    )

    val state = rememberDatePickerState()
    var open by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate: String? = datePickerState.selectedDateMillis?.let { millis ->
        Instant.fromEpochMilliseconds(millis).toString()
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { open = true
            println("12") }) {
        OutlinedTextField(
            value = selectedDate?.toString() ?: "",
            onValueChange = {},
            label = { Text("Дата") },
            enabled = false, // поле неактивно
            modifier = Modifier
                .fillMaxWidth()
                .clickable { open = true
                    println("helll") },
            readOnly = true,
            trailingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) }
        )

        if (open) {
            DatePickerDialog(
                onDismissRequest = { open = false },
                confirmButton = {
                    TextButton(onClick = { open = false }) { Text("OK") }
                },
                dismissButton = {
                    TextButton(onClick = { open = false }) { Text("Отмена") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}