package com.github.onotoliy.opposite.ui.events.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.events.models.EventCreateModel
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
actual fun EventCreateView(
    viewModel: EventCreateModel,
    onSelect: (Screen) -> Unit
) = EventCreateMobileView(viewModel, onSelect)
