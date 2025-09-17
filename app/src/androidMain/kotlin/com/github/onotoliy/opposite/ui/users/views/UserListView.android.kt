package com.github.onotoliy.opposite.ui.users.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.models.UserListModel

@Composable
actual fun UserListView(
    viewModel: UserListModel,
    onSelect: (Screen) -> Unit
) = UserListMobileView(viewModel, onSelect)