package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.components.events.views.EventInformationMobileView
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource

@Composable
actual fun UserInformationView(
    user: User,
    logo: DrawableResource, onSelect: (Screen) -> Unit
) = UserInformationMobileView(user, logo, onSelect)