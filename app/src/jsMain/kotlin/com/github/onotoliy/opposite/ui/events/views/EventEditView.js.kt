package com.github.onotoliy.opposite.ui.events.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.events.models.EventEditModel
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
actual fun EventEditView(
    viewModel: EventEditModel,
    onSelect: (Screen) -> Unit
)= EventEditWebView(viewModel, onSelect)