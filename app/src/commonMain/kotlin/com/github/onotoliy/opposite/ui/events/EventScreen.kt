package com.github.onotoliy.opposite.ui.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.viewmodel.UiState
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EventViewScreen(
    uuid: String,
    model: EventEditModel = koinViewModel { parametersOf(uuid) }
) {
    val state by model.state.collectAsState()

    UiStateScreen<Event>(state, load = model::load) { event ->
        val name = rememberTextFieldState(event.name)
        val author = rememberTextFieldState(event.author.name)
        val deadline = rememberTextFieldState(event.deadline)
        val contribution = rememberTextFieldState(event.contribution)
        val creationDate = rememberTextFieldState(event.creationDate)

        Column {
            OutlinedTextField(state = name, readOnly = true, label = { Text(text = "Название") })
            OutlinedTextField(state = author, readOnly = true, label = { Text(text = "Автор") })
            OutlinedTextField(
                state = deadline, readOnly = true,
                label = { Text(text = "Сдать до") }
            )
            OutlinedTextField(
                state = contribution, readOnly = true,
                label = { Text(text = "Сумма") })
            OutlinedTextField(
                state = creationDate, readOnly = true,
                label = { Text(text = "Дата создания") }
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
        val name = rememberTextFieldState(event.name)
        val deadline = rememberTextFieldState(event.deadline)
        val contribution = rememberTextFieldState(event.contribution)

        Column {
            OutlinedTextField(state = name, label = { Text(text = "Название") })
            OutlinedTextField(
                state = deadline,
                label = { Text(text = "Сдать до") }
            )
            OutlinedTextField(
                state = contribution,
                label = { Text(text = "Сумма") })

        }
    }
}
