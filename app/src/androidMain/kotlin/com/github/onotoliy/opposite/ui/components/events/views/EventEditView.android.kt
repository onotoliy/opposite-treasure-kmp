package com.github.onotoliy.opposite.ui.components.events.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel

@Composable
actual fun EventEditView(viewModel: EventEditModel, onSelect: (Screen) -> Unit) =
    EventEditMobileView(viewModel, onSelect)