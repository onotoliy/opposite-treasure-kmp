package com.github.onotoliy.opposite.ui.components.events

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
expect fun EventListView(events: List<Event>, onSelect: (Screen) -> Unit)