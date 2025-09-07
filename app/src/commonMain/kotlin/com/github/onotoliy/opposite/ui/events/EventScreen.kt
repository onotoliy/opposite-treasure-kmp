package com.github.onotoliy.opposite.ui.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.viewmodel.UiState
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
@ExperimentalMaterial3Api
fun EventViewScreen(
    uuid: String,
    model: EventEditModel = koinViewModel { parametersOf(uuid) }
) {
    val state by model.state.collectAsState()

    UiStateScreen<Event>(state, load = model::load) { event ->
        var name by remember { mutableStateOf(event.name) }
        var author by remember { mutableStateOf(event.author.name) }
        var deadline by remember { mutableStateOf(event.deadline) }
        var contribution by remember { mutableStateOf(event.contribution) }
        var creationDate by remember { mutableStateOf(event.creationDate) }

        Column {
            OutlinedTextField(
                value = name,
                readOnly = true,
                label = { Text(text = "Название") },
                onValueChange = {}
            )
            OutlinedTextField(
                value = author,
                readOnly = true,
                label = { Text(text = "Автор") },
                onValueChange = {}
            )
            OutlinedTextField(
                value = deadline,
                readOnly = true,
                label = { Text(text = "Сдать до") },
                onValueChange = {}
            )
            OutlinedTextField(
                value = contribution,
                readOnly = true,
                label = { Text(text = "Сумма") },
                onValueChange = {}
            )
            OutlinedTextField(
                value = creationDate,
                readOnly = true,
                label = { Text(text = "Дата создания") },
                onValueChange = {}
            )
        }
    }
}

@Composable
fun EventEditScreen(uuid: String) {
    val model: EventEditModel = koinViewModel { parametersOf(uuid) }
    val state by model.state.collectAsState()

    UiStateScreen<Event>(state, load = model::load) { event ->
        val event = (state as UiState.Success<Event>).data
        val name by remember { mutableStateOf(event.name) }
        val deadline by remember { mutableStateOf(event.deadline) }
        val contribution by remember { mutableStateOf(event.contribution) }

        Column {
            OutlinedTextField(value = name, label = { Text(text = "Название") }, onValueChange = {})
            OutlinedTextField(
                value = deadline,
                label = { Text(text = "Сдать до") }, onValueChange = {}
            )
            OutlinedTextField(
                value = contribution,
                label = { Text(text = "Сумма") }, onValueChange = {})

        }
    }
}
