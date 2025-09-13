package com.github.onotoliy.opposite.ui.components.users.screens

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.components.users.UserCreateView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.users.UserNewViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserNewScreen(model: UserNewViewModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    ApplicationScaffold(onSelect = onSelect) {
        UserCreateView(model, onSelect)
    }
}