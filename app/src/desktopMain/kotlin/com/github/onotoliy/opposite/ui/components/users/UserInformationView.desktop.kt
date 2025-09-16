package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.Deposit
import org.jetbrains.compose.resources.DrawableResource

@Composable
actual fun UserInformationView(deposit: Deposit, logo: DrawableResource) =
    UserInformationWebView(deposit, logo)