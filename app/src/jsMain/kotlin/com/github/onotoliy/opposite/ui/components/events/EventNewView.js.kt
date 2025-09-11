package com.github.onotoliy.opposite.ui.components.events

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.events.views.EventEditWebView
import com.github.onotoliy.opposite.ui.components.events.views.EventCreateWebView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import com.github.onotoliy.opposite.viewmodel.events.EventNewViewModel

@Composable
actual fun EventCreateView(viewModel: EventNewViewModel, onSelect: (Screen) -> Unit) =
    EventCreateWebView(viewModel, onSelect)

@Composable
actual fun EventEditView(viewModel: EventEditModel, onSelect: (Screen) -> Unit) =
    EventEditWebView(viewModel, onSelect)