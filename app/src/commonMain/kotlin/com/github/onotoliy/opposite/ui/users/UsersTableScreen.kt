package com.github.onotoliy.opposite.ui.users

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.components.UserTableScreen
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventsListModel
import com.github.onotoliy.opposite.viewmodel.users.UsersListModel
import io.github.windedge.table.DataTable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UsersTableScreen(model: UsersListModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    val state by model.state.collectAsState()

    UiStateScreen<List<User>>(state, load = model::load) { events ->
        UsersTableScreen(events, onSelect)
    }
}

@Composable
fun UsersTableScreen(users: List<User>, onSelect: (Screen) -> Unit) {
    UserTableScreen(users, onSelect)
}
