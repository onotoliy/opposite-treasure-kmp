package com.github.onotoliy.opposite.ui.events.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.events.models.EventListAdapter
import com.github.onotoliy.opposite.ui.events.views.EventListView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EventListScreen(list: EventListAdapter = koinInject(), nav: NavController) {
    ApplicationScaffold(onSelect = nav::goto) {
        EventListView(list, nav::goto)
    }
}

