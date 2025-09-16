package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.Deposit
import com.github.onotoliy.opposite.ui.components.users.list.WebUserTableComponent
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
actual fun UserListView(deposits: List<Deposit>, onSelect: (Screen) -> Unit) =
    WebUserTableComponent(deposits, onSelect)