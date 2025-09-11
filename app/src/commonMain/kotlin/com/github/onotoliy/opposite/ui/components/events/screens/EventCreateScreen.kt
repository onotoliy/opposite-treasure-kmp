package com.github.onotoliy.opposite.ui.components.events.screens

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.components.events.views.EventCreateView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventNewViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EventNewScreen(model: EventNewViewModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    ApplicationScaffold(onSelect = onSelect) {
        EventCreateView(model, onSelect)
    }
}