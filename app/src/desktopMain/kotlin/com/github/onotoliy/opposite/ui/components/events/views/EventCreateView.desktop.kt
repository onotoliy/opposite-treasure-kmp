package com.github.onotoliy.opposite.ui.components.events.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventCreateModel

@Composable
actual fun EventCreateView(viewModel: EventCreateModel, onSelect: (Screen) -> Unit) =
    EventCreateWebView(viewModel, onSelect)