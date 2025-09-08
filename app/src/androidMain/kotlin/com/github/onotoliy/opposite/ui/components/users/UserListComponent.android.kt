package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.components.users.list.UserListMobileView
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
actual fun UserListView(users: List<User>, onSelect: (Screen) -> Unit) =
    UserListMobileView(users, onSelect)