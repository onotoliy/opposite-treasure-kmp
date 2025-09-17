package com.github.onotoliy.opposite.ui.events.screens

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.events.models.EventEditModel
import com.github.onotoliy.opposite.ui.events.views.EventEditView
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EventEditScreen(
    uuid: String,
    model: EventEditModel = koinViewModel { parametersOf(uuid) },
    onSelect: (Screen) -> Unit
) {
    ApplicationScaffold(onSelect = onSelect) {
        EventEditView(model, onSelect)
    }
}
