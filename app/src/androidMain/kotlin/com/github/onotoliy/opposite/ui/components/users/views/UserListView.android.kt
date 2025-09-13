package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.users.UsersListModel

@Composable
actual fun UserListView(
    viewModel: UsersListModel, onSelect: (Screen) -> Unit
) = UserListMobileView(viewModel, onSelect)