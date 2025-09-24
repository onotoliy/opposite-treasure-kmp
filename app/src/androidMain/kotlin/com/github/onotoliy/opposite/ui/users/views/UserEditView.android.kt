package com.github.onotoliy.opposite.ui.users.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.models.UserEditModel

@Composable
actual fun UserEditView(
    viewModel: UserEditModel,
    onSelect: (Screen) -> Unit
) = UserEditMobileView(viewModel, onSelect)
