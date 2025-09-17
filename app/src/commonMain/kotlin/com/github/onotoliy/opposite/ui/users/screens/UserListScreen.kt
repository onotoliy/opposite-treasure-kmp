package com.github.onotoliy.opposite.ui.users.screens

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.models.UserListModel
import com.github.onotoliy.opposite.ui.users.views.UserListView
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserListScreen(model: UserListModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    ApplicationScaffold(onSelect = onSelect) {
        UserListView(model, onSelect)
    }
}

