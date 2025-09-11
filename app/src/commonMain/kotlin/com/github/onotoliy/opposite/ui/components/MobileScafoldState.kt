package com.github.onotoliy.opposite.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf

class MobileScafoldState {
    var topBar by mutableStateOf<(@Composable () -> Unit)?>(null)
    var floatingActionButton by mutableStateOf<(@Composable () -> Unit)?>(null)
}

val LocalMobileScafoldState = staticCompositionLocalOf<MobileScafoldState> {
    error("No TopBarState provided")
}
