package com.github.onotoliy.opposite.ui.components.events.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventNewViewModel

@Composable
actual fun EventCreateView(viewModel: EventNewViewModel, onSelect: (Screen) -> Unit) =
    EventCreateMobileView(viewModel, onSelect)