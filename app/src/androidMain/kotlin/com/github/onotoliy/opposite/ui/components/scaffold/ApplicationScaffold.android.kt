package com.github.onotoliy.opposite.ui.components.scaffold

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
actual fun ApplicationScaffold(
    onSelect: (Screen) -> Unit,
    content: @Composable (() -> Unit)
) = ApplicationMobileScaffold(onSelect, content)