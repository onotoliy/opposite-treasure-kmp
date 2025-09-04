package com.github.opposite.treasure.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import io.github.aleksandar_stefanovic.composematerialdatatable.ColumnSpec
//import io.github.aleksandar_stefanovic.composematerialdatatable.Table
//import io.github.aleksandar_stefanovic.composematerialdatatable.TextColumnSpec
//import io.github.aleksandar_stefanovic.composematerialdatatable.WidthSetting

val store: IEventRepository = EventCacheRepository()

@Composable
fun EventScreen(event: Event, isModify: Boolean) {
    val name = rememberTextFieldState(event.name)
    val author = rememberTextFieldState(event.author.name)
    val deadline = rememberTextFieldState(event.deadline)
    val contribution = rememberTextFieldState(event.contribution)
    val creationDate = rememberTextFieldState(event.creationDate)

    Column {
        Text(text = "Название:")
        OutlinedTextField(state = name, enabled = isModify)
        Text(text = "Автор:")
        OutlinedTextField(state = author, enabled = isModify)
        Text(text = "Сдать до:")
        OutlinedTextField(state = deadline, enabled = isModify)
        Text(text = "Сумма:")
        OutlinedTextField(state = contribution, enabled = isModify)
        Text(text = "Дата создания:")
        OutlinedTextField(state = creationDate, enabled = isModify)
    }
}

@Composable
fun EventsListScreen(events: List<Event>) {
    events.forEach {
        EventItemScreen(it)
    }
}

@Composable
fun EventItemScreen(event: Event) {
    Row {
        Column {
            Text(text = event.name)
            Divider()
        }
    }
}

//@Composable
//fun EventsTableScreen(events: List<Event>) {
//    MaterialTheme {
//        val columnSpecs = listOf<ColumnSpec<Event, *>>(
//            TextColumnSpec("Название", WidthSetting.Flex(1f)) { it.name },
//            TextColumnSpec("Автор", WidthSetting.WrapContent, { it.author.name }),
//            TextColumnSpec("Сдать до", WidthSetting.WrapContent, valueSelector = { it.deadline }),
//            TextColumnSpec("Сумма", WidthSetting.WrapContent, { it.contribution }),
//            TextColumnSpec("Дата создания", WidthSetting.WrapContent, { it.creationDate })
//        )
//
//        Column {
//            var selectedCount by remember { mutableStateOf(0) }
//
//            Table(
//                columnSpecs,
//                events,
//                modifier = Modifier.padding(20.dp),
//                showSelectionColumn = true,
//                onSelectionChange = { list -> selectedCount = list.size }
//            )
//
//            if (selectedCount > 0) {
//                Text("Selected: $selectedCount")
//            }
//
//        }
//    }
//}