package com.github.onotoliy.opposite.ui.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.Event

@Composable
fun EventViewScreen(event: Event) = EventScreen(event, false)

@Composable
fun EventEditScreen(event: Event) = EventScreen(event, true)

@Composable
private fun EventScreen(event: Event, isModify: Boolean) {
    val name = rememberTextFieldState(event.name)
    val author = rememberTextFieldState(event.author.name)
    val deadline = rememberTextFieldState(event.deadline)
    val contribution = rememberTextFieldState(event.contribution)
    val creationDate = rememberTextFieldState(event.creationDate)

    Column {
        OutlinedTextField(state = name, enabled = isModify, label = { Text(text = "Название") })
        OutlinedTextField(state = author, enabled = isModify, label = { Text(text = "Автор") })
        OutlinedTextField(
            state = deadline,
            enabled = isModify,
            label = { Text(text = "Сдать до") }
        )
        OutlinedTextField(
            state = contribution,
            enabled = isModify,
            label = { Text(text = "Сумма") })
        OutlinedTextField(
            state = creationDate,
            enabled = isModify,
            label = { Text(text = "Дата создания") }
        )
    }
}
