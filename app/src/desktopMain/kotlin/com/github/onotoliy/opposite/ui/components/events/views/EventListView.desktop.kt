package com.github.onotoliy.opposite.ui.components.events.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.Page
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventsListModel

@Composable
actual fun EventListView(viewModel: EventsListModel, onSelect: (Screen) -> Unit) =
    EventTableWebView(viewModel, onSelect)