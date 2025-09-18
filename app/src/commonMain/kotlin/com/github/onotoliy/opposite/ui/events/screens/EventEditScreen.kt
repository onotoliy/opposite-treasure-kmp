package com.github.onotoliy.opposite.ui.events.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.events.models.EventEditModel
import com.github.onotoliy.opposite.ui.events.views.EventEditView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EventEditScreen(
    uuid: String,
    nav: NavController,
    model: EventEditModel = koinViewModel { parametersOf(uuid) }
) {
    ApplicationScaffold(onSelect = nav::goto) {
        EventEditView(model, nav::goto)
    }
}
