package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.components.users.view.UserInformationWebView
import org.jetbrains.compose.resources.DrawableResource

@Composable
actual fun UserInformationView(user: User, logo: DrawableResource) =
    UserInformationWebView(user, logo)