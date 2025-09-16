package com.github.onotoliy.opposite.ui.components.users.screens

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.components.events.views.EventEditView
import com.github.onotoliy.opposite.ui.components.users.UserEditView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import com.github.onotoliy.opposite.viewmodel.users.UserEditModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun UserEditScreen(
    uuid: String,
    model: UserEditModel = koinViewModel { parametersOf(uuid) },
    onSelect: (Screen) -> Unit
) {
    ApplicationScaffold(onSelect = onSelect) {
        UserEditView(model, onSelect)
    }
}
