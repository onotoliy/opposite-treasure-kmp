package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.users.UserCreateModel

@Composable
actual fun UserCreateView(viewModel: UserCreateModel, onSelect: (Screen) -> Unit) =
    UserCreateMobileView(viewModel, onSelect)