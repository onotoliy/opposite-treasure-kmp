package com.github.onotoliy.opposite.ui.components.events.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventListModel

@Composable
actual fun EventListView(viewModel: EventListModel, onSelect: (Screen) -> Unit) =
    EventTableWebView(viewModel, onSelect)