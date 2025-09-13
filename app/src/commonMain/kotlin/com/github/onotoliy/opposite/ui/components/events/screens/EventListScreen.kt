package com.github.onotoliy.opposite.ui.components.events.screens

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.components.events.views.EventListView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventListModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EventListScreen(model: EventListModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    ApplicationScaffold(onSelect = onSelect) {
        EventListView(model, onSelect)
    }
}

