package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.users.UserListModel

@Composable
actual fun UserListView(
    viewModel: UserListModel, onSelect: (Screen) -> Unit
) = UserListMobileView(viewModel, onSelect)