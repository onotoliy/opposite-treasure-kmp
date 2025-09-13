package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.users.UserEditModel

@Composable
actual fun UserEditView(
    viewModel: UserEditModel,
    onSelect: (Screen) -> Unit
) = UserEditMobileView(viewModel, onSelect)