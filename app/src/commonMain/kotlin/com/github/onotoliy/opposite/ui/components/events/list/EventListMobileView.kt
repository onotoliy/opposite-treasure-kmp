package com.github.onotoliy.opposite.ui.components.events.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
fun EventListMobileView(events: List<Event>, onSelect: (Screen) -> Unit) {
    LazyColumn {
        items(events.size) {
            EventMobileItem(events[it], onSelect)
        }
    }
}

@Composable
private fun EventMobileItem(event: Event, onSelect: (Screen) -> Unit) {
    ElevatedCard(
        modifier = Modifier.clickable { onSelect(Screen.EventViewScreen(event.uuid)) }
    ) {
        Row {
            Icon(imageVector = Icons.Outlined.Event, contentDescription = null)

            Column(modifier = Modifier.weight(1f)) {
                Text(text = event.name)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = event.contribution)
                    Text(text = event.deadline)
                }
            }
        }
    }
}