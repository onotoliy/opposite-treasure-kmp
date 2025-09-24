package com.github.onotoliy.opposite.ui.users.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.models.UserListAdapter
import com.github.onotoliy.opposite.ui.users.models.UserListModel

@Composable
actual fun UserListView(
    listAdapter: UserListAdapter, hasActionButtons: Boolean
) = UserTableWebView(listAdapter.table, hasActionButtons)

