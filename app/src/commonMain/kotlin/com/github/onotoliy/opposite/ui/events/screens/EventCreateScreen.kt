package com.github.onotoliy.opposite.ui.events.screens

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.events.models.EventCreateModel
import com.github.onotoliy.opposite.ui.events.views.EventCreateView
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EventCreateScreen(model: EventCreateModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    ApplicationScaffold(onSelect = onSelect) {
        EventCreateView(model, onSelect)
    }
}
