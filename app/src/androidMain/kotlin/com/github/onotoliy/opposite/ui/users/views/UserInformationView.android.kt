package com.github.onotoliy.opposite.ui.users.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.models.UserViewModel
import org.jetbrains.compose.resources.DrawableResource

@Composable
actual fun UserInformationView(
    viewModel: UserViewModel
) = UserInformationMobileView(viewModel)
