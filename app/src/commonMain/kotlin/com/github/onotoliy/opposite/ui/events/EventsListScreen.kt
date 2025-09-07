package com.github.onotoliy.opposite.ui.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.Event

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
