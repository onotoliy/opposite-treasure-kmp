package com.github.onotoliy.opposite.ui.users.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource

@Composable
actual fun UserInformationView(
    deposit: Deposit,
    logo: DrawableResource,
    onSelect: (Screen) -> Unit
) = UserInformationWebView(deposit, logo, onSelect)