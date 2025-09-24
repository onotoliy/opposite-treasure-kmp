package com.github.onotoliy.opposite.ui.users.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.models.UserCreateModel

@Composable
actual fun UserCreateView(
    viewModel: UserCreateModel
) = UserCreateWebView(viewModel)
