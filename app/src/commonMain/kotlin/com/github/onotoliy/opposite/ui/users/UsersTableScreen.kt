package com.github.onotoliy.opposite.ui.users

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.components.users.UserListView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.users.UsersListModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UsersTableScreen(model: UsersListModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    val state by model.state.collectAsState()

    UiStateScreen<List<User>>(state, load = model::load) { events ->
        ApplicationScaffold(
            onSelect = onSelect
        ) {
            UserListView(events, onSelect)
        }
    }
}

