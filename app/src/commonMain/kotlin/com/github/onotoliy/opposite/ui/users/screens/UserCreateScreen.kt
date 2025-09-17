package com.github.onotoliy.opposite.ui.users.screens

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.models.UserCreateModel
import com.github.onotoliy.opposite.ui.users.views.UserCreateView
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserCreateScreen(model: UserCreateModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    ApplicationScaffold(onSelect = onSelect) {
        UserCreateView(model, onSelect)
    }
}