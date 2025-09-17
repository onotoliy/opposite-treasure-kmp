package com.github.onotoliy.opposite.ui.users.screens

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.models.UserEditModel
import com.github.onotoliy.opposite.ui.users.views.UserEditView
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
