package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.components.users.view.UserInformationMobileView
import org.jetbrains.compose.resources.DrawableResource

@Composable
actual fun UserInformationView(user: User, logo: DrawableResource) =
    UserInformationMobileView(user, logo)