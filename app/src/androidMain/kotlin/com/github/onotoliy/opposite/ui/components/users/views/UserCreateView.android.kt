package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.users.UserNewViewModel

@Composable
actual fun UserCreateView(viewModel: UserNewViewModel, onSelect: (Screen) -> Unit) =
    UserCreateMobileView(viewModel, onSelect)