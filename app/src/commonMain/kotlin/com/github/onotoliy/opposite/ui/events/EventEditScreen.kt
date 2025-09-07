package com.github.onotoliy.opposite.ui.events

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.viewmodel.UiState
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

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
