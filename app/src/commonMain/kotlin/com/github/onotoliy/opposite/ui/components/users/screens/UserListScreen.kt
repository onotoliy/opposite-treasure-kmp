package com.github.onotoliy.opposite.ui.users

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.components.users.UserListView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.users.UserListModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserListScreen(model: UserListModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    ApplicationScaffold(onSelect = onSelect) {
        UserListView(model, onSelect)
    }
}

