package com.github.onotoliy.opposite.ui.events.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.events.models.EventListModel
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
actual fun EventListView(
    viewModel: EventListModel,
    onSelect: (Screen) -> Unit
) = EventTableWebView(viewModel, onSelect)