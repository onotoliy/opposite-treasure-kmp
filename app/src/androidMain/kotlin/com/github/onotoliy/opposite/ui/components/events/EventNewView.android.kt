package com.github.onotoliy.opposite.ui.components.events.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.events.views.EventEditMobileView
import com.github.onotoliy.opposite.ui.components.events.views.EventCreateMobileView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import com.github.onotoliy.opposite.viewmodel.events.EventNewViewModel

@Composable
actual fun EventCreateView(viewModel: EventNewViewModel, onSelect: (Screen) -> Unit) =
    EventCreateMobileView(viewModel, onSelect)

@Composable
actual fun EventEditView(viewModel: EventEditModel, onSelect: (Screen) -> Unit) =
    EventEditMobileView(viewModel, onSelect)